package com.avrisnox.gamedev.build.Avalanche.utils;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class RawPolyManager {
	private List<Integer> vaos = new LinkedList<>();
	private List<Integer> vbos = new LinkedList<>();
	private List<RawPoly> polys = new LinkedList<>();

	public void renderPolys() {
		for(RawPoly poly : polys) {
			glBindVertexArray(poly.getVaoId());
			GL30.glEnableVertexAttribArray(0);
			GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, poly.getVertexCount());
			GL30.glDisableVertexAttribArray(0);
			glBindVertexArray(0);
		}
	}

	public void loadPoly(float[] positions) {
		int vaoId = createVao();
		vaos.add(vaoId);
		storeDataInAttributes(0, positions);
		unbindVao();
		polys.add(new RawPoly(vaoId, positions.length / 3));
	}

	public void close() {
		for(Integer vao : vaos)
			GL30.glDeleteVertexArrays(vao);

		for(Integer vbo : vbos)
			GL30.glDeleteBuffers(vbo);
	}

	private int createVao() {
		int vaoId = GL30.glGenVertexArrays();
		glBindVertexArray(vaoId);
		return vaoId;
	}

	private void storeDataInAttributes(int attributeIndex, float[] data) {
		int vboId = GL30.glGenBuffers();
		vbos.add(vboId);
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboId);
		FloatBuffer buffer = floatsToBuffer(data);
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, buffer, GL30.GL_STATIC_DRAW);
		GL30.glVertexAttribPointer(attributeIndex, 3, GL30.GL_FLOAT, false, 0, 0);
		GL30.glBindVertexArray(0);
	}

	private void unbindVao() {
		glBindVertexArray(0);
	}

	private FloatBuffer floatsToBuffer(float... data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		return buffer.put(data).flip();
	}
}
