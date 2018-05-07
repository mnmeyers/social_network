package com.michal.network.service;

import java.io.File;

public interface EntityService {

    void ingestData(File entityRelationships, File entityProperties);
}
