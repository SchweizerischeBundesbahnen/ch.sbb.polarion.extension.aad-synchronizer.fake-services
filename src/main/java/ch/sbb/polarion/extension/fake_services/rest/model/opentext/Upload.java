package ch.sbb.polarion.extension.fake_services.rest.model.opentext;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;

import java.io.InputStream;

public record Upload(String fileName, String nodeId, String parentId, long size, String ticket, boolean versionUpdate) {

    @SneakyThrows
    public static Upload fromValues(String fileName, String nodeId, String parentId, InputStream size, String ticket, boolean versionUpdate) {
        return new Upload(fileName, nodeId, parentId, size == null ? 0 : IOUtils.copy(size, NullOutputStream.INSTANCE), ticket, versionUpdate);
    }

}
