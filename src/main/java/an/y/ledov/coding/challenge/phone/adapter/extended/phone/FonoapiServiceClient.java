package an.y.ledov.coding.challenge.phone.adapter.extended.phone;

import an.y.ledov.coding.challenge.phone.adapter.extended.phone.dto.RestDevice;
import an.y.ledov.coding.challenge.phone.adapter.extended.phone.dto.RestToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "fonoapiServiceClient", url = "${feign.clients.fonoapi}")
public interface FonoapiServiceClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "/token/generate",
            produces = MediaType.APPLICATION_JSON_VALUE)
    RestToken getToken();

    @RequestMapping(method = RequestMethod.GET,
            value = "/v1/getdevice",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<RestDevice> getDevices(
        @RequestParam String token,
        @RequestParam String brand,
        @RequestParam String device,
        @RequestParam String position);
}
