package ch.sbb.polarion.extension.fake_services.rest.model.opentext;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class UploadTest {

    @Test
    @SneakyThrows
    void testUploadWithNonEmptyStream() {
        String content = "Hello World!"; // 12 bytes
        ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        Upload upload = Upload.fromValues("file.txt", "123", "456", in, "ticket-1", false);

        assertEquals("file.txt", upload.fileName());
        assertEquals("123", upload.nodeId());
        assertEquals("456", upload.parentId());
        assertEquals(content.getBytes(StandardCharsets.UTF_8).length, upload.size());
        assertEquals("ticket-1", upload.ticket());
        assertFalse(upload.versionUpdate());
    }

    @Test
    @SneakyThrows
    void testUploadWithEmptyStream() {
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[0]);
        Upload upload = Upload.fromValues("empty.txt", "n1", "p1", in, "t-2", false);

        assertEquals(0, upload.size());
        assertEquals("empty.txt", upload.fileName());
        assertEquals("n1", upload.nodeId());
        assertEquals("p1", upload.parentId());
        assertEquals("t-2", upload.ticket());
        assertFalse(upload.versionUpdate());
    }

    @Test
    @SneakyThrows
    void testUploadWithNullStream() {
        Upload upload = Upload.fromValues("null.bin", "n2", "p2", null, "t-3", false);
        assertEquals(0, upload.size());
        assertEquals("null.bin", upload.fileName());
        assertEquals("n2", upload.nodeId());
        assertEquals("p2", upload.parentId());
        assertEquals("t-3", upload.ticket());
        assertFalse(upload.versionUpdate());
    }

    @Test
    @SneakyThrows
    void testVersionUpdateUpload() {
        String content = "New Version"; // 11 bytes
        ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        Upload upload = Upload.fromValues(null, "node-xyz", null, in, "ticket-4", true);

        assertNull(upload.fileName());
        assertEquals("node-xyz", upload.nodeId());
        assertNull(upload.parentId());
        assertEquals(content.getBytes(StandardCharsets.UTF_8).length, upload.size());
        assertEquals("ticket-4", upload.ticket());
        assertTrue(upload.versionUpdate());
    }

}
