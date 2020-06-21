package com.avrisnox.gamedev.build.Avalanche.utils;

public class RawPoly {
	private int vaoId;
	private int vertexCount;

	public RawPoly(int vaoId, int vertexCount) {
		this.vaoId = vaoId;
		this.vertexCount = vertexCount;
	}

	public int getVaoId() {
		return vaoId;
	}

	public int getVertexCount() {
		return vertexCount;
	}
}
