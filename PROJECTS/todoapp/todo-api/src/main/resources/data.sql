-- Batch 1: Developer todos
INSERT INTO todos (title, description, status, created_at, updated_at) VALUES
('Spring Boot', 'Learn Spring Boot fundamentals and best practices including JPA, REST APIs, and testing', 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('API Docs', 'Create comprehensive API documentation using Swagger/OpenAPI for all endpoints', 'IN_PROGRESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Auth', 'Add Spring Security with JWT tokens for user authentication and authorization', 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Unit Tests', 'Create comprehensive test suite covering service layer, repository layer, and controllers', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Deploy', 'Configure production environment with PostgreSQL database and proper security settings', 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CI/CD', 'Configure GitHub Actions for automated testing and deployment', 'IN_PROGRESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Caching', 'Implement Redis caching for better performance on frequently accessed data', 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Mobile API', 'Create mobile-friendly API endpoints and test with React Native app', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Emails', 'Review team updates, support tickets, and project discussions', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Standup', 'Share progress, blockers, and today''s plan with the team', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);