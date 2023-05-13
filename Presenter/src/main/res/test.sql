--
-- File
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Chapters
CREATE TABLE IF NOT EXISTS Chapters (Id INTEGER PRIMARY KEY NOT NULL, Title TEXT (64), Style INTEGER, "Desc" TEXT (512), Progress INTEGER, "Order" INTEGER);
INSERT INTO Chapters (Id, Title, Style, "Desc", Progress, "Order") VALUES (0, 'Title title title', 0, 'Description description description', 0, 0);
INSERT INTO Chapters (Id, Title, Style, "Desc", Progress, "Order") VALUES (1, 'Title', 0, 'Second Description', 0, 1);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
