-- =========================
-- USERS
-- =========================

INSERT INTO users ( login, password) VALUES
('alice', 'alice123'),
('bob', 'bob123'),
('charlie', 'charlie123'),
('david', 'david123'),
('emma', 'emma123');


-- =========================
-- CHATROOMS
-- =========================

INSERT INTO chatrooms (name, owner_id) VALUES
('General', 1),
('Java', 2),
('C++', 3),
('Web Development', 4),
('Gaming', 5);


-- =========================
-- CHATROOM USERS
-- =========================

INSERT INTO chatroom_users (user_id, room_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(1, 2),
(2, 2),
(4, 2),
(3, 3),
(5, 4);


-- =========================
-- MESSAGES
-- =========================

INSERT INTO messages (author_id, room_id, text, created_at) VALUES
(1, 1, 'Hello everyone!', '2026-09-28 10:00:00'),
(2, 1, 'Hello Alice!', '2026-09-28 10:05:00'),
(3, 1, 'How is everyone doing?', '2026-09-28 10:10:00'),
(4, 2, 'I have a question about Java.', '2026-09-28 11:00:00'),
(5, 2, 'Sure, what is your question?', '2026-09-28 11:05:00');