INSERT INTO authors (id, name, email, country, biography) VALUES
(1, 'Maya Hart', 'maya.hart@example.com', 'United States', 'Writes practical guides about software craft and systems thinking.'),
(2, 'Arjun Mehta', 'arjun.mehta@example.com', 'India', 'Focuses on data platforms and engineering leadership.'),
(3, 'Lena Ortiz', 'lena.ortiz@example.com', 'Spain', 'Creates accessible books about design, teams, and product delivery.'),
(4, 'Noah Chen', 'noah.chen@example.com', 'Canada', 'Documents modern Java patterns for web applications.'),
(5, 'Priya Raman', 'priya.raman@example.com', 'Singapore', 'Writes about cloud architecture and resilient services.'),
(6, 'Oliver Smith', 'oliver.smith@example.com', 'United Kingdom', 'Publishes beginner-friendly programming books.'),
(7, 'Aisha Khan', 'aisha.khan@example.com', 'Pakistan', 'Explores database modeling and analytics workflows.'),
(8, 'Ethan Brooks', 'ethan.brooks@example.com', 'Australia', 'Writes concise field manuals for agile teams.'),
(9, 'Sofia Rossi', 'sofia.rossi@example.com', 'Italy', 'Specializes in user experience and research operations.'),
(10, 'Kenji Tanaka', 'kenji.tanaka@example.com', 'Japan', 'Covers automation, testing, and delivery pipelines.');

INSERT INTO books (id, title, isbn, genre, publication_year, price, author_id) VALUES
(1, 'Spring Boot in Practice', '978-1000000001', 'Technology', 2024, 39.99, 4),
(2, 'Designing Calm Interfaces', '978-1000000002', 'Design', 2022, 29.50, 3),
(3, 'Data Modeling Essentials', '978-1000000003', 'Database', 2021, 34.75, 7),
(4, 'Cloud Systems Playbook', '978-1000000004', 'Cloud', 2023, 44.00, 5),
(5, 'Testing Without Fear', '978-1000000005', 'Testing', 2020, 31.25, 10),
(6, 'The Product Delivery Notebook', '978-1000000006', 'Management', 2023, 27.99, 8),
(7, 'Java for New Builders', '978-1000000007', 'Programming', 2024, 24.99, 6),
(8, 'Engineering Teams That Learn', '978-1000000008', 'Leadership', 2022, 36.20, 2),
(9, 'Research Ops Field Guide', '978-1000000009', 'UX Research', 2021, 28.40, 9),
(10, 'Readable Systems', '978-1000000010', 'Software Engineering', 2025, 41.80, 1);

ALTER TABLE authors ALTER COLUMN id RESTART WITH 11;
ALTER TABLE books ALTER COLUMN id RESTART WITH 11;

