-- Sample users: one maker, one checker (Password is hashed: 'password')
INSERT INTO users (username, password, role) VALUES
('maker1', '$2a$10$gF/gTayJ1c9rKXG4VGJSxOxJ6dFvN2Xb9vMB7yzCzAxCsnZfrDNUO', 'MAKER'),
('checker1', '$2a$10$gF/gTayJ1c9rKXG4VGJSxOxJ6dFvN2Xb9vMB7yzCzAxCsnZfrDNUO', 'CHECKER');
