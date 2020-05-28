package com.avrisnox.gamedev.mvc.controller;

import com.avrisnox.gamedev.mvc.util.ApiVersions;

public enum Controllers {
	DUMBELL(ApiVersions.v1_0)
	;
	private ApiVersions apiVersion;

	private Controllers(ApiVersions apiVersion) {
		this.apiVersion = apiVersion;
	}

	public ApiVersions getApiVersion() {
		return apiVersion;
	}
}
