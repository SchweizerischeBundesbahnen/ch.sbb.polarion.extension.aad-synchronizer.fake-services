package ch.sbb.polarion.extension.fake_services.rest.model.opentext;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    @Test
    void testConstructor() {
        // given
        String id = "123";
        String name = "Test Container";
        String nodeId = "node-456";
        String folder = "/test/folder";

        // when
        Container container = new Container(id, name, nodeId, folder);

        // then
        assertEquals(id, container.id());
        assertEquals(name, container.name());
        assertEquals(nodeId, container.nodeId());
        assertEquals(folder, container.folder());
    }

    @Test
    void testEquals_equalObjects() {
        // given
        Container container1 = new Container("1", "Name", "node1", "/folder");
        Container container2 = new Container("1", "Name", "node1", "/folder");

        // then
        assertEquals(container1, container2);
        assertEquals(container2, container1);
    }

    @Test
    void testEquals_differentObjects() {
        // given
        Container container1 = new Container("1", "Name", "node1", "/folder");
        Container container2 = new Container("2", "Name", "node1", "/folder");

        // then
        assertNotEquals(container1, container2);
    }

    @Test
    void testEquals_nullObject() {
        // given
        Container container = new Container("1", "Name", "node1", "/folder");

        // then
        assertNotEquals(null, container);
    }


    @Test
    void testHashCode_equalObjects() {
        // given
        Container container1 = new Container("1", "Name", "node1", "/folder");
        Container container2 = new Container("1", "Name", "node1", "/folder");

        // then
        assertEquals(container1.hashCode(), container2.hashCode());
    }

    @Test
    void testToString() {
        // given
        Container container = new Container("123", "TestName", "node456", "/test");

        // when
        String result = container.toString();

        // then
        assertNotNull(result);
        assertTrue(result.contains("123"));
        assertTrue(result.contains("TestName"));
        assertTrue(result.contains("node456"));
        assertTrue(result.contains("/test"));
    }

    @Test
    void testWithNullValues() {
        // given & when
        Container container = new Container(null, null, null, null);

        // then
        assertNull(container.id());
        assertNull(container.name());
        assertNull(container.nodeId());
        assertNull(container.folder());
    }

    @Test
    void testWithEmptyStrings() {
        // given
        Container container = new Container("", "", "", "");

        // then
        assertEquals("", container.id());
        assertEquals("", container.name());
        assertEquals("", container.nodeId());
        assertEquals("", container.folder());
    }

    @Test
    void testPartialNullValues() {
        // given
        Container container = new Container("1", null, "node1", null);

        // then
        assertEquals("1", container.id());
        assertNull(container.name());
        assertEquals("node1", container.nodeId());
        assertNull(container.folder());
    }

}
