Example of Spring Boot with Apache Kafka and Cloud Events.

Fields recommend in CloudEvents and used in this sample

* ID (id): Unique identifier for the event, aiding traceability.
* Source (source): URI that describes the origin of the event.
* Type (type): Type of event (e.g., "com.sacavix.orders.OrderCreated").
* Data Content Type (datacontenttype): MIME type of the data, such as application/json.
* Subject (optional): Event context, useful for subcategories.
* Time (time): Timestamp when the event occurred.

These fields help identify, classify, and process events efficiently.