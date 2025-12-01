package ch.sbb.polarion.extension.fake_services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FakeServicesAdminUiServletTest {

    @Test
    void testInitialization() {
        assertDoesNotThrow(FakeServicesAdminUiServlet::new);
    }

}
