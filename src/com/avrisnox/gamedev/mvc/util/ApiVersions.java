package com.avrisnox.gamedev.mvc.util;

public enum ApiVersions {
	v1_0("1.0", true, false)
	;
	private String versionId;
	private boolean development;
	private boolean experimental;

	private ApiVersions(String versionId, boolean development, boolean experimental) {
		this.versionId = versionId;
		this.development = development;
		this.experimental = experimental;
	}

	public String getVersionId() {
		return versionId;
	}

	public boolean isDevelopment() {
		return development;
	}

	public boolean isExperimental() {
		return experimental;
	}
}
