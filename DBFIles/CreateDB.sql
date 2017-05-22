DROP TABLE IF EXISTS public.users;

CREATE TABLE public.users
(
  user_name VARCHAR(25) PRIMARY KEY NOT NULL,
  name      VARCHAR(25)             NOT NULL,
  surname   VARCHAR(25)             NOT NULL,
  email     VARCHAR(50)             NOT NULL,
  password  VARCHAR(255)            NOT NULL
);
CREATE UNIQUE INDEX users_email_uindex
  ON public.users (email);

CREATE TABLE public.movies (
  id           INT PRIMARY KEY NOT NULL,
  poster       VARCHAR(100)    NOT NULL,
  title        VARCHAR(100)    NOT NULL,
  genders      VARCHAR(100)    NOT NULL,
  release_year DATE            NOT NULL,
  rating_imdb  DECIMAL         NOT NULL
);