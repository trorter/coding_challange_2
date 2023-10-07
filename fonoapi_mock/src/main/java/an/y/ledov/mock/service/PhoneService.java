package an.y.ledov.mock.service;

import an.y.ledov.mock.rest.dto.RestDevice;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    private final static List<RestDevice> devices = List.of(
        RestDevice.builder()
            .device("Apple iPhone 12 Pro Max")
            .brand("Apple")
            .position("1")
            .technology("GSM / CDMA / HSPA / EVDO / LTE / 5G")
            .gprs("Yes")
            .edge("Yes")
            .announced("2020, October 13")
            .status("Available. Released 2020, November 13")
            .dimensions("160.8 x 78.1 x 7.4 mm (6.33 x 3.07 x 0.29 in)")
            .weight_g("228 g (8.04 oz)")
            .weight_oz("Glass front (Gorilla Glass), glass back (Gorilla Glass), stainless steel frame")
            .build(),
        RestDevice.builder()
            .device("Samsung Galaxy S9")
            .brand("Samsung")
            .position("2")
            .technology("GSM / CDMA / HSPA / EVDO / LTE / 5G")
            .gprs("Yes")
            .edge("Yes")
            .announced("2018, February 25")
            .status("Available. Released 2018, March 16")
            .dimensions("147.7 x 68.7 x 8.5 mm (5.81 x 2.70 x 0.33 in)")
            .weight_g("163 g (5.75 oz)")
            .weight_oz("Front/back glass (Gorilla Glass 5), aluminum frame")
            .build(),
        RestDevice.builder()
            .device("Apple iPhone 13")
            .brand("Apple")
            .position("3")
            .technology("GSM / CDMA / HSPA / EVDO / LTE / 5G")
            .gprs("Yes")
            .edge("Yes")
            .announced("2021, September 14")
            .status("Available. Released 2021, September 24")
            .dimensions("146.7 x 71.5 x 7.7 mm (5.78 x 2.81 x 0.30 in)")
            .weight_g("174 g (6.14 oz)")
            .weight_oz("Glass front (Gorilla Glass), glass back (Gorilla Glass), aluminum frame")
            .build(),
        RestDevice.builder()
            .device("Nokia 3310 3G")
            .brand("Nokia")
            .position("4")
            .technology("GSM")
            .gprs("No")
            .edge("No")
            .announced("2017, September")
            .build()
    );
    public List<RestDevice> findDevices(
        String brand,
        @NonNull String device,
        String position) {

        return devices.stream()
            .filter(restDevice -> restDevice.getDevice().toLowerCase().contains(device.toLowerCase()))
            .filter(restDevice -> brand == null || restDevice.getBrand().toLowerCase().contains(brand.toLowerCase()))
            .filter(restDevice -> position == null || restDevice.getPosition().contains(position))
            .toList();
    }
}
