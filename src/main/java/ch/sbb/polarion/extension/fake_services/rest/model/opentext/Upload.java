package ch.sbb.polarion.extension.fake_services.rest.model.opentext;

import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;

import java.io.InputStream;

@Data
public class Upload {

    private String fileName;
    private String nodeId;
    private String parentId;
    private long size;
    private String ticket;
    private boolean versionUpdate;

    @SneakyThrows
    public Upload(String fileName, String nodeId, String parentId, InputStream fileStream, String ticket, boolean versionUpdate) {
        this.fileName = fileName;
        this.nodeId = nodeId;
        this.parentId = parentId;
        this.size = fileStream == null ? 0 : IOUtils.copy(fileStream, NullOutputStream.INSTANCE);
        this.ticket = ticket;
        this.versionUpdate = versionUpdate;
    }

}
