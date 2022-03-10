# NBP-service
Service created using NBP API, Spring Boot and Java, exposing following endpoints:
- GET /api/exchange-rates/{currencyCode} - returns currency exchange rate PLN to {currencyCode} for the last 5 business days.
- GET /api/gold-price/average - returns average gold price for the last 14 business days.

## How to open and run project
1. Clone repository on your device.
2. Navigate to src/main/java/com.test.nbpservice and set NbPserviceApplication as your Run/Debug Configuration.
3. Run application.
4. Service should run on localhost:8080 by default (if not, check console - you will find port number there).
5. In browser write address which interests you, e.g. :
    - For showing PLN to GBP exchange rates for the last 5 business days.
    
    ```
    localhost:8080/api/exchange-rates/gbp
    ```

    - For showing PLN to USD exchange rates for the last 5 business days.
    
    ```
    localhost:8080/api/exchange-rates/usd
    ```

    - For showing average gold price for the last 14 business days in PLN.
    ```
    localhost:8080/api/gold-price/average
    ```
