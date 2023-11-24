CREATE TABLE IF NOT EXISTS USERDATA (
    SharedKey VARCHAR(255) PRIMARY KEY,
    BusinessID VARCHAR(255),
    Email VARCHAR(255),
    Phone VARCHAR(20),
    StartDate DATE,
    EndDate DATE,
    DateAdded DATE
);

-- Añadir una restricción UNIQUE a BusinessID para asegurar que no haya duplicados
ALTER TABLE USERDATA ADD CONSTRAINT UK_BusinessID UNIQUE (BusinessID);

-- Insertar datos de prueba
INSERT INTO USERDATA (SharedKey, BusinessID, Email, Phone, EndDate,StartDate, DateAdded)
VALUES
    ('sebastianm', 'Sebastian', 'sebastianm@example.com', '123-456-7891', '2023-11-24', '2023-11-24', '2023-11-24'),
    ('moncaleanom', 'Moncaleano', 'moncaleanom@example.com', '123-456-7892', '2023-11-24', '2023-11-24', '2023-11-24'),
    ('mosqueram', 'Mosquera', 'mosqueram@example.com', '123-456-78910', '2023-11-24', '2023-11-24', '2023-11-24');