# Internship — Payment Processing System

A microservices-based payment processing platform built with **Java 17** and **Spring Boot**, integrating **Stripe** as the payment provider. This repository contains the work completed during the internship program.

## Repository Structure

```
repo/
├── Payment-processing-service/     # Core payment orchestration service
│   ├── payment-processing-service/           # Main service (latest)
│   └── payment-processing-service-getting-started/  # Initial setup
├── Stripe-provider-service/        # Stripe integration layer
│   ├── stripe-provider-service/              # Main service (latest)
│   ├── stripe-provider-initial-setup/
│   ├── stripe-provider-get-session/
│   ├── stripe-provider-expire-session-new/
│   └── stripe-provider-service-week-5/
└── db/
    └── db-setup/                   # Database DDL & DML scripts
        └── spring2/
            ├── ddl/ddl-script.sql
            └── dml/dml-script.sql
```

## Services Overview

### Payment Processing Service
Handles the end-to-end payment lifecycle:
- Create and initiate transactions
- Process payment status updates (Created → Initiated → Pending → Success/Failed)
- Send notifications on status changes
- Communicate with the Stripe Provider Service

### Stripe Provider Service
Acts as an adapter between the payment system and Stripe:
- Create Stripe Checkout sessions
- Retrieve and expire payment sessions
- Handle Stripe webhook events asynchronously

### Database Setup
SQL scripts for setting up the payment database schema and seed data.

## Tech Stack

| Component        | Technology              |
|------------------|-------------------------|
| Language         | Java 17                 |
| Framework        | Spring Boot             |
| Build Tool       | Maven                   |
| Payment Provider | Stripe                  |
| Database         | MySQL                   |

## Prerequisites

- Java 17+
- Maven 3.6+
- MySQL
- Stripe account (test API keys)
- [Stripe CLI](https://stripe.com/docs/stripe-cli) (for local webhook testing)

## Getting Started

### 1. Database Setup

Run the SQL scripts in order:

```bash
mysql -u root -p < db/db-setup/spring2/ddl/ddl-script.sql
mysql -u root -p < db/db-setup/spring2/dml/dml-script.sql
```

### 2. Configure Local Properties

Each service requires an `application-local.properties` file (not committed to git for security). Create one in each service's `src/main/resources/` directory with your local settings:

```properties
# Example for stripe-provider-service
stripe.api.key=<YOUR_STRIPE_TEST_API_KEY>
stripe.webhook.secret=<YOUR_STRIPE_WEBHOOK_SECRET>
spring.datasource.url=jdbc:mysql://localhost:3306/<your_db>
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
```

### 3. Run the Services

```bash
# Stripe Provider Service
cd Stripe-provider-service/stripe-provider-service
./mvnw spring-boot:run

# Payment Processing Service (in a separate terminal)
cd Payment-processing-service/payment-processing-service
./mvnw spring-boot:run
```

### 4. Stripe Webhooks (Local Development)

Forward Stripe events to your local webhook endpoint:

```bash
stripe listen --forward-to localhost:<port>/webhook/stripe
```

## Project Progression

The repository includes multiple versions of each service reflecting incremental development:

| Stage | Folder |
|-------|--------|
| Getting started | `payment-processing-service-getting-started` |
| Stripe initial setup | `stripe-provider-initial-setup` |
| Get session | `stripe-provider-get-session` |
| Expire session | `stripe-provider-expire-session-new` |
| Week 5 milestone | `stripe-provider-service-week-5` |
| **Latest (production-ready)** | `payment-processing-service` & `stripe-provider-service` |

## Author

**Jairaj Kolhatkar**

GitHub: [JairajKolhatkar](https://github.com/JairajKolhatkar)
