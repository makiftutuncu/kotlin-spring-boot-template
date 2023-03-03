CREATE TABLE cats
(
    id         UUID PRIMARY KEY,
    version    INTEGER                  NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    name       TEXT                     NOT NULL,
    breed      TEXT                     NOT NULL,
    age        INTEGER                  NOT NULL,
    -- We have a composite unique index of "name + breed + age" to define a unique cat.
    -- Including "deleted_at" in the constraint lets us do soft-deletion.
    UNIQUE NULLS NOT DISTINCT (name, breed, age, deleted_at)
);
