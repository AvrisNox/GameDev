package com.avrisnox.gamedev.mvc.model;

import com.avrisnox.gamedev.mvc.util.ApiVersions;

public enum Models {
	JANUS(ApiVersions.v1_0)
	;
	private ApiVersions apiVersion;

	private Models(ApiVersions apiVersion) {
		this.apiVersion = apiVersion;
	}

	public ApiVersions getApiVersion() {
		return apiVersion;
	}
}
