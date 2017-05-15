DROP TABLE IF EXISTS public.users;

CREATE TABLE public.users
(
  user_name VARCHAR(25) PRIMARY KEY NOT NULL,
  name      VARCHAR(25)             NOT NULL,
  sure_name VARCHAR(25)             NOT NULL,
  email     VARCHAR(50)             NOT NULL,
  password  VARCHAR(255)            NOT NULL
);
CREATE UNIQUE INDEX users_email_uindex
  ON public.users (email);