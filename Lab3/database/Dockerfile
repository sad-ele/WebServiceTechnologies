FROM postgres:16
#FROM arm64v8/postgres

ENV POSTGRES_USER user
ENV POSTGRES_PASSWORD 12345678
ENV POSTGRES_DB postgresbd

COPY init_db.sql /docker-entrypoint-initdb.d/