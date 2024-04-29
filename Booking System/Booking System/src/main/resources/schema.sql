CREATE TABLE IF NOT EXISTS authors (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL
    );


CREATE TABLE IF NOT EXISTS publishers (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL
    );
CREATE TABLE IF NOT EXISTS book (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
    book_authors_id INT,
    publishers_id INT,
    FOREIGN KEY (book_authors_id) REFERENCES publishers(id),
    FOREIGN KEY (publishers_id) REFERENCES publishers(id)
    );
CREATE TABLE IF NOT EXISTS book_authors (
                                            book_id INT,
                                            author_id INT,
                                            FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (author_id) REFERENCES authors(id)
    );
