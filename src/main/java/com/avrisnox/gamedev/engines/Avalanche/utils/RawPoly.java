package com.avrisnox.gamedev.engines.Avalanche.utils;

class RawPoly {
	private int vaoId;
	private int vertexCount;

	RawPoly(int vaoId, int vertexCount) {
		this.vaoId = vaoId;
		this.vertexCount = vertexCount;
	}

	int getVaoId() {
		return vaoId;
	}

	int getVertexCount() {
		return vertexCount;
	}
}
