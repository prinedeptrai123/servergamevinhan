package com.vinhan.ptgameserver.storage;

import com.teso.framework.common.Config;
import com.vinhan.ptgameserver.config.ConfigInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = ConfigInfo.UPLOAD_PATH + ConfigInfo.UPLOAD_FOLDER;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
