package com.avrisnox.gamedev.mvc.view;

import com.avrisnox.gamedev.mvc.util.ApiVersions;

public enum Views {
	QUERCUS(ApiVersions.v1_0)
	;
	private ApiVersions apiVersion;

	private Views(ApiVersions apiVersion) {
		this.apiVersion = apiVersion;
	}

	public ApiVersions getApiVersion() {
		return apiVersion;
	}
}
