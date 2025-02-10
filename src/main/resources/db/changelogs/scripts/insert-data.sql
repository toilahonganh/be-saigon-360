INSERT INTO users (id, email, name, address, phone_number, image_url, token, expiry, status, created_by, updated_by) VALUES
('user1', 'user1@example.com', 'John Doe', '288M1 Nam Ki Khoi Nghia, Vo Thi Sau, District 3, HCMC', '0383702585', 'http://example.com/images/user1.png', 'sample_token_1', '2024-12-31T23:59:59', true, 'system', 'system');

INSERT INTO roles (id, name, description, slug, created_by, updated_by) VALUES
('role1', 'Admin', 'Administrator role', 'admin', 'system', 'system'),
('role2', 'User', 'Standard user role', 'user', 'system', 'system');

INSERT INTO menu_function (id, name, active, created_by, updated_by) VALUES
('menu1', 'Destinations', true, 'system', 'system'),
('menu2', 'VR', true, 'system', 'system');

INSERT INTO sub_function (id, name, description, active, url, mfunc_id, created_by, updated_by) VALUES
('sub1', 'Ben Thanh Market', 'Ben Thanh Market', true, '/destinations/ben-thanh-market', 'menu1', 'system', 'system'),
('sub2', 'Metro Ben Thanh - Suoi Tien', 'Ben Thanh Market', true, '/destinations/metro-ben-thanh-suoi-tien', 'menu1', 'system', 'system'),
('sub3', 'Saigon Notre Dame Cathedral', 'Saigon Notre Dame Cathedral', true, '/destinations/saigon-notre-dame-cathedral', 'menu1', 'system', 'system');



INSERT INTO user_roles (user_id, role_id, created_by, updated_by) VALUES
('user1', 'role1', 'system', 'system');

