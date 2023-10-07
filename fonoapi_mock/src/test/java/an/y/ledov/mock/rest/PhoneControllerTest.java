package an.y.ledov.mock.rest;

import an.y.ledov.mock.rest.advice.GlobalExceptionHandler;
import an.y.ledov.mock.rest.dto.RestDevice;
import an.y.ledov.mock.rest.dto.RestErrorResponse;
import an.y.ledov.mock.service.PhoneService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {
    PhoneController.class,
    GlobalExceptionHandler.class,
    PhoneService.class})
public class PhoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    @DisplayName("should successfully call get device endpoint")
    void shouldSuccessfullyCallGetDeviceEndpoint() {
        var requestUrl = "/v1/getdevice/?token={token}&brand={brand}&device={device}";

        var result = mockMvc.perform(get(
                requestUrl,
                "token",
                "apple",
                "iphone")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        var token = new ObjectMapper().readValue(
            result.getResponse().getContentAsString(),
            new TypeReference<List<RestDevice>>(){});

        assertThat(token).isNotNull();
        assertThat(token).isNotEmpty();

        var firstDevice = token.get(0);

        assertThat(firstDevice).isNotNull();
        assertThat(firstDevice.getDevice()).isNotNull();
        assertThat(firstDevice.getDevice()).isNotEmpty();
        assertThat(firstDevice.getBrand()).isNotNull();
        assertThat(firstDevice.getBrand()).isNotEmpty();
        assertThat(firstDevice.getPosition()).isNotNull();
        assertThat(firstDevice.getPosition()).isNotEmpty();
    }

    @Test
    @SneakyThrows
    @DisplayName("should successfully call get device endpoint with empty result")
    void shouldSuccessfullyCallGetDeviceEndpointWithEmptyResult() {
        var requestUrl = "/v1/getdevice/?token={token}&brand={brand}&device={device}";

        var result = mockMvc.perform(get(
                requestUrl,
                "token",
                "appleAndroid",
                "iphone")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        var token = new ObjectMapper().readValue(
            result.getResponse().getContentAsString(),
            new TypeReference<List<RestDevice>>(){});

        assertThat(token).isNotNull();
        assertThat(token).isEmpty();
    }

    @Test
    @SneakyThrows
    @DisplayName("should call get device endpoint with bad request response")
    void shouldCallGetDeviceEndpointWithBadRequestResponse() {
        var requestUrl = "/v1/getdevice/?&brand={brand}&device={device}";

        var result = mockMvc.perform(get(
                requestUrl,
                "apple",
                "iphone")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        var token = new ObjectMapper().readValue(
            result.getResponse().getContentAsString(),
            new TypeReference<RestErrorResponse>(){});

        assertThat(token).isNotNull();
        assertThat(token.getErrorMessage()).isNotNull();
    }

}
