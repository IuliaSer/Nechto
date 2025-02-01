
CREATE TABLE if not exists GAMES (
                        id SERIAL NOT NULL,
                        date TIMESTAMP,
                        PRIMARY KEY (id)
);

CREATE TABLE if not exists  USERS (
                      id SERIAL NOT NULL,
                      name varchar(255) NOT NULL,
                      username varchar(255) NOT NULL,
                      password varchar(255) NOT NULL,
                      role varchar(255) NOT NULL,
                      PRIMARY KEY (id),
                      UNIQUE(name, username)
);

CREATE TABLE if not exists  SCORES (
                        id SERIAL NOT NULL,
                        user_id int,
                        game_id int,
                        scores numeric default 0,
                        PRIMARY KEY (id),
                        UNIQUE(user_id, game_id),
                        FOREIGN KEY (user_id) REFERENCES USERS(id),
                        FOREIGN KEY (game_id) REFERENCES GAMES(id)
);

create table if not exists  SCORES_STATUS (
                                  scores_id int not null,
                                  status_id varchar(255) not null,
                                  FOREIGN KEY (scores_id) REFERENCES SCORES(id)

);

COMMIT;
