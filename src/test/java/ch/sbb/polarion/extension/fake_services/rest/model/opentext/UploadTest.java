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
        Upload upload = new Upload("file.txt", "123", "456", in, "ticket-1", false);

        assertEquals("file.txt", upload.getFileName());
        assertEquals("123", upload.getNodeId());
        assertEquals("456", upload.getParentId());
        assertEquals(content.getBytes(StandardCharsets.UTF_8).length, upload.getSize());
        assertEquals("ticket-1", upload.getTicket());
        assertFalse(upload.isVersionUpdate());
    }

    @Test
    @SneakyThrows
    void testUploadWithEmptyStream() {
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[0]);
        Upload upload = new Upload("empty.txt", "n1", "p1", in, "t-2", false);

        assertEquals(0, upload.getSize());
        assertEquals("empty.txt", upload.getFileName());
        assertEquals("n1", upload.getNodeId());
        assertEquals("p1", upload.getParentId());
        assertEquals("t-2", upload.getTicket());
        assertFalse(upload.isVersionUpdate());
    }

    @Test
    @SneakyThrows
    void testUploadWithNullStream() {
        Upload upload = new Upload("null.bin", "n2", "p2", null, "t-3", false);
        assertEquals(0, upload.getSize());
        assertEquals("null.bin", upload.getFileName());
        assertEquals("n2", upload.getNodeId());
        assertEquals("p2", upload.getParentId());
        assertEquals("t-3", upload.getTicket());
        assertFalse(upload.isVersionUpdate());
    }

    @Test
    @SneakyThrows
    void testVersionUpdateUpload() {
        String content = "New Version"; // 11 bytes
        ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        Upload upload = new Upload(null, "node-xyz", null, in, "ticket-4", true);

        assertNull(upload.getFileName());
        assertEquals("node-xyz", upload.getNodeId());
        assertNull(upload.getParentId());
        assertEquals(content.getBytes(StandardCharsets.UTF_8).length, upload.getSize());
        assertEquals("ticket-4", upload.getTicket());
        assertTrue(upload.isVersionUpdate());
    }

}
