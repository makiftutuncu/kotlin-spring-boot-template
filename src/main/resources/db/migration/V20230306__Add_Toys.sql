CREATE TABLE toys
(
    id         UUID PRIMARY KEY,
    version    INTEGER                  NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    cat_id     UUID                     NOT NULL REFERENCES cats(id),
    name       TEXT                     NOT NULL,
    -- Toy names are unique for each cat (cat_id + name).
    -- Including "deleted_at" in the constraint lets us do soft-deletion.
    UNIQUE NULLS NOT DISTINCT (cat_id, name, deleted_at)
);
