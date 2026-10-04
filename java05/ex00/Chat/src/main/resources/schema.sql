DROP TABLE IF EXISTS chatroom_users;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS chatrooms;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE chatrooms (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    owner_id BIGINT NOT NULL,

    FOREIGN KEY (owner_id) REFERENCES users(id)
);


CREATE TABLE messages (
    id BIGSERIAL PRIMARY KEY,
    author_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (author_id) REFERENCES users(id),
    FOREIGN KEY (room_id) REFERENCES chatrooms(id)
);

CREATE TABLE chatroom_users (
    user_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, room_id),

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (room_id) REFERENCES chatrooms(id)
);