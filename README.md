-- Create the database 
CREATE DATABASE DB_SazonIa; 
USE DB_SazonIa; 

CREATE TABLE User ( 
	id INT AUTO_INCREMENT PRIMARY KEY, 
    first_name VARCHAR(50) NOT NULL, 
    last_name_father VARCHAR(50) NOT NULL, 
    last_name_mother VARCHAR(50) NOT NULL, 
    birth_date DATE NOT NULL, 
    phone_number VARCHAR(15) NOT NULL, 
    email VARCHAR(100) NOT NULL UNIQUE, 
    password VARCHAR(255) NOT NULL 
); 

CREATE TABLE ProfilePicture ( 
	picture_id INT AUTO_INCREMENT PRIMARY KEY, 
    id INT, 
    image_url VARCHAR(255) NOT NULL, -- Almacena el ID de la imagen de MongoDB 
    upload_time DATETIME NOT NULL, 
    CONSTRAINT FK_ProfilePicture_User FOREIGN KEY (id) 
    REFERENCES User(id)ON DELETE CASCADE 
); 
    
-- Create the Profession table 
CREATE TABLE Profession ( 
	profession_id INT AUTO_INCREMENT PRIMARY KEY, 
    profession VARCHAR(100) NOT NULL 
); 

-- Create the ChefLicense table with foreign keys to User and Profession 
CREATE TABLE ChefLicense ( 
	ChefLicense_id INT AUTO_INCREMENT PRIMARY KEY, 
    id INT, 
    profession_id INT, 
    license VARCHAR(50) NOT NULL, 
    gender VARCHAR(10), 
    profession VARCHAR(100), 
    year_of_issue YEAR, 
    institution VARCHAR(100), 
    CONSTRAINT FK_ChefLicense_User FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE, 
    CONSTRAINT FK_ChefLicense_Profession FOREIGN KEY (profession_id) REFERENCES Profession(profession_id) 
); 

-- Create the Token table with a foreign key to User 
CREATE TABLE Token ( 
	token_id INT AUTO_INCREMENT PRIMARY KEY, 
	id INT, reset_token VARCHAR(255), 
    token_expiration DATETIME, 
    CONSTRAINT FK_Token_User FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE 
); 

-- Create an index on the profession field in ChefLicense 
CREATE INDEX idx_profession ON ChefLicense (profession); 
-- Create a unique index on the reset_token field 
CREATE UNIQUE INDEX idx_reset_token ON Token (reset_token); 

CREATE TABLE Follower ( 
	id INT NOT NULL, 
    followed_id INT NOT NULL, 
    PRIMARY KEY (id, followed_id), 
    FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE, 
    FOREIGN KEY (followed_id) REFERENCES User(id) ON DELETE CASCADE, 
    UNIQUE (id, followed_id) 
); 

CREATE TABLE OpenAIRequest ( 
	request_id INT PRIMARY KEY AUTO_INCREMENT, 
    id INT NOT NULL, prompt VARCHAR(500) NOT NULL, 
    recommendations VARCHAR(1000), 
    requestDate DATE NOT NULL, 
    FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE 
); 

CREATE TABLE FavoritePost ( 
	id INT NOT NULL, 
	post_id VARCHAR(36) NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	PRIMARY KEY (id, post_id), -- Clave primaria compuesta 
	FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE 
); 

CREATE TABLE Recipe( 
	recipe_id INT NOT NULL AUTO_INCREMENT, 
    recipe_name VARCHAR(100) NOT NULL, 
    ingredients VARCHAR(250) NOT NULL, 
    instructions VARCHAR(500)NOT NULL, 
    preparation_time VARCHAR(20) NOT NULL, 
    difficulty VARCHAR(10), 
    recipe_time_stamp DATETIME NOT NULL, 
    id INT NOT NULL, PRIMARY KEY (recipe_id), 
    FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE 
); 

CREATE TABLE CommentRecipe( 
	comment_id INT NOT NULL AUTO_INCREMENT, 
    content VARCHAR(300) NOT NULL, 
    comment_time_stamp DATETIME NOT NULL, 
    id INT NOT NULL, 
    recipe_id INT NOT NULL, 
    PRIMARY KEY (comment_id), 
    FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE, 
    FOREIGN KEY (recipe_id) REFERENCES Recipe(recipe_id) ON DELETE CASCADE 
); 

CREATE TABLE ReplyCommentRecipe( 
	reply_id INT NOT NULL AUTO_INCREMENT, 
    content VARCHAR(300) NOT NULL, 
    reply_time_stamp DATETIME NOT NULL, 
    id INT NOT NULL, comment_id INT NOT NULL, 
    PRIMARY KEY (reply_id), 
    FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE, 
    FOREIGN KEY (comment_id) REFERENCES CommentRecipe(comment_id) ON DELETE CASCADE 
); 

-- Insert data 
INSERT INTO User (first_name, last_name_father, last_name_mother, birth_date, phone_number, email, password) 
VALUES ('Juan2', 'Perez', 'Lopez', '1990-01-01', '555-1234', 'juan2.perez@example.com', 'securepassword'), 
('Juan1', 'Perez', 'Lopez', '1990-01-01', '555-1234', 'juan1.perez@example.com', 'securepassword'), 
('Juan3', 'Perez', 'Lopez', '1990-01-01', '555-1234', 'juan3.perez@example.com', 'securepassword'), 
('Juan4', 'Perez', 'Lopez', '1990-01-01', '555-1234', 'juan4.perez@example.com', 'securepassword'); 

INSERT INTO ProfilePicture (id, mongo_image_id, upload_time) 
VALUES (1, 'someMongoImageId12345', NOW()); 

INSERT INTO Profession (profession) 
VALUES ('Chef'); 

INSERT INTO ChefLicense (id, profession_id, license, gender, profession, year_of_issue, institution) 
VALUES (1, 1, 'CHEF12345', 'Male', 'Chef', 2020, 'Culinary Institute'); 

INSERT INTO Token (id, reset_token, token_expiration) 
VALUES (1, 'randomToken123', '2024-12-31 23:59:59');
 
INSERT INTO Follower (id, followed_id) 
VALUES (4, 1); 

INSERT INTO OpenAIRequest (id, prompt, recommendations, requestDate) 
VALUES (1, 'Generate a recipe for lasagna', 'Use fresh ingredients', '2024-10-17'); 

select * from OpenAIRequest;
 
-- Queries 
SELECT * FROM User; 
SELECT * FROM ProfilePicture; 
SELECT * FROM ChefLicense; 
SELECT * FROM Profession; 
SELECT * FROM Token; 
SELECT * FROM Follower; 

-- Drop the database (optional, not recommended in development) 
DROP DATABASE DB_SazonIa; 
DROP TABLE User; 
DROP TABLE ChefLicense; 
DROP TABLE Profession; 
DROP TABLE Token; 
DROP TABLE Follower; 
DROP TABLE OpenAIRequest;
