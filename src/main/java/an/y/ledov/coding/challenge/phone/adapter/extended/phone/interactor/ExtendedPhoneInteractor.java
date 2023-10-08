package an.y.ledov.coding.challenge.phone.adapter.extended.phone.interactor;

import an.y.ledov.coding.challenge.phone.adapter.extended.phone.FonoapiServiceClient;
import an.y.ledov.coding.challenge.phone.domain.model.phone.Phone;
import an.y.ledov.coding.challenge.phone.domain.service.ExtendedPhoneService;
import an.y.ledov.coding.challenge.phone.mapping.PhoneMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExtendedPhoneInteractor implements ExtendedPhoneService {

    private final PhoneMapper phoneMapper;

    private final FonoapiServiceClient fonoapiServiceClient;

    public Optional<Phone.ExtendedParams> getExtendedInformation(String name) {
        try {
            var token = fonoapiServiceClient.getToken();
            log.info("Token received: {}", token);

            var devices = fonoapiServiceClient.getDevices(
                token.getToken(),
                null,
                name,
                null);

            log.info("Devices received: {}", devices);

            return devices.isEmpty()
                ? Optional.empty()
                : Optional.of(phoneMapper.toExtendedParams(devices.get(0)));

        } catch (FeignException.BadRequest e) {
            log.error("Bad request to fonoapi service", e);
            return Optional.empty();

        } catch (FeignException.GatewayTimeout e) {
            log.error("Gateway timeout to fonoapi service", e);
            return Optional.empty();

        } catch (Exception e) {
            log.error("Unexpected exception", e);
            return Optional.empty();
        }
    }
}
