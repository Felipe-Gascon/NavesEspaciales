
CREATE TABLE naves_espaciales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    serieOmovie VARCHAR(255) NOT NULL
);

INSERT INTO naves_espaciales (nombre, serieOmovie) VALUES ('Enterprise', 'Star Trek');
INSERT INTO naves_espaciales (nombre, serieOmovie) VALUES ('Millennium Falcon', 'Star Wars');
INSERT INTO naves_espaciales (nombre, serieOmovie) VALUES ('Battlestar Galactica', 'Battlestar Galactica');
