# up-features
Sample API for some features

## How to
- build and run:
```docker build -t up-features . && docker run -it -p 8080:8080 up-features```
- explore and test:
test UI available at http://localhost:8080/swagger-ui/.
OpenApi docs at http://localhost:8080/v3/api-docs .



## Optional improvements
- clean code: data type for timestamp in Features-class could be e.g. Instant.
- test-coverage: SourceParser coverage can be improved (edge-cases like empty or malformed json etc.)
- security: remove error stacktrace from API response if required.