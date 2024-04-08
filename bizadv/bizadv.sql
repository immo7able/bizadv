--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

-- Started on 2024-04-08 14:47:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 24615)
-- Name: anime; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.anime (
    anime_id integer NOT NULL,
    name character varying(100) NOT NULL,
    seriescount integer,
    status character varying(20) NOT NULL,
    studio character varying(50) NOT NULL,
    typeofanime character varying(20) NOT NULL,
    sourceofanime character varying(20) NOT NULL,
    avatar character varying(50),
    background character varying(50),
    rating double precision,
    views integer,
    outdate timestamp with time zone
);


ALTER TABLE public.anime OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24618)
-- Name: anime_anime_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.anime_anime_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.anime_anime_id_seq OWNER TO postgres;

--
-- TOC entry 4845 (class 0 OID 0)
-- Dependencies: 216
-- Name: anime_anime_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.anime_anime_id_seq OWNED BY public.anime.anime_id;


--
-- TOC entry 217 (class 1259 OID 24619)
-- Name: animecomment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animecomment (
    animecomment_id integer NOT NULL,
    user_id integer,
    anime_id integer,
    comment character varying(250)
);


ALTER TABLE public.animecomment OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24622)
-- Name: animecomments_animecomment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animecomments_animecomment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animecomments_animecomment_id_seq OWNER TO postgres;

--
-- TOC entry 4846 (class 0 OID 0)
-- Dependencies: 218
-- Name: animecomments_animecomment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animecomments_animecomment_id_seq OWNED BY public.animecomment.animecomment_id;


--
-- TOC entry 219 (class 1259 OID 24623)
-- Name: animegenre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animegenre (
    animegenre_id integer NOT NULL,
    genre_id integer,
    anime_id integer
);


ALTER TABLE public.animegenre OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24626)
-- Name: animegenres_animegenre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animegenres_animegenre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animegenres_animegenre_id_seq OWNER TO postgres;

--
-- TOC entry 4847 (class 0 OID 0)
-- Dependencies: 220
-- Name: animegenres_animegenre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animegenres_animegenre_id_seq OWNED BY public.animegenre.animegenre_id;


--
-- TOC entry 221 (class 1259 OID 24627)
-- Name: genre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genre (
    genre_id integer NOT NULL,
    name character varying(20)
);


ALTER TABLE public.genre OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 24630)
-- Name: genres_genre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.genres_genre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.genres_genre_id_seq OWNER TO postgres;

--
-- TOC entry 4848 (class 0 OID 0)
-- Dependencies: 222
-- Name: genres_genre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.genres_genre_id_seq OWNED BY public.genre.genre_id;


--
-- TOC entry 225 (class 1259 OID 24635)
-- Name: userlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.userlist (
    userlist_id integer NOT NULL,
    user_id integer,
    anime_id integer,
    listtype character varying(20) NOT NULL
);


ALTER TABLE public.userlist OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24631)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    email character varying(50),
    ranking character varying(50),
    reg_date timestamp with time zone,
    role character varying(10)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 24634)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 4849 (class 0 OID 0)
-- Dependencies: 224
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 226 (class 1259 OID 24638)
-- Name: userslist_userlist_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.userslist_userlist_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.userslist_userlist_id_seq OWNER TO postgres;

--
-- TOC entry 4850 (class 0 OID 0)
-- Dependencies: 226
-- Name: userslist_userlist_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.userslist_userlist_id_seq OWNED BY public.userlist.userlist_id;


--
-- TOC entry 4659 (class 2604 OID 24639)
-- Name: anime anime_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.anime ALTER COLUMN anime_id SET DEFAULT nextval('public.anime_anime_id_seq'::regclass);


--
-- TOC entry 4660 (class 2604 OID 24640)
-- Name: animecomment animecomment_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animecomment ALTER COLUMN animecomment_id SET DEFAULT nextval('public.animecomments_animecomment_id_seq'::regclass);


--
-- TOC entry 4661 (class 2604 OID 24641)
-- Name: animegenre animegenre_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animegenre ALTER COLUMN animegenre_id SET DEFAULT nextval('public.animegenres_animegenre_id_seq'::regclass);


--
-- TOC entry 4662 (class 2604 OID 24642)
-- Name: genre genre_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genre ALTER COLUMN genre_id SET DEFAULT nextval('public.genres_genre_id_seq'::regclass);


--
-- TOC entry 4664 (class 2604 OID 24644)
-- Name: userlist userlist_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userlist ALTER COLUMN userlist_id SET DEFAULT nextval('public.userslist_userlist_id_seq'::regclass);


--
-- TOC entry 4663 (class 2604 OID 24643)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 4828 (class 0 OID 24615)
-- Dependencies: 215
-- Data for Name: anime; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.anime VALUES (9, 'name', 5, 'ongoing', 'studio', 'ona', 'source', 'cf94dd28-c01b-4f11-bca4-bc11dcce65cd.pic1.jpg', '6bda0822-0ba9-41a8-88d7-051ec629ae1f.banner2.jpg', 0, 0, '2024-04-08 00:51:28.9+06');
INSERT INTO public.anime VALUES (10, 'name2', 5, 'completed', 'studio', 'special', 'source', '1b2a5b6c-023c-4c4d-9bad-32a9317ef1a8.pic4.jpg', '3b3c9109-1e08-403c-b838-421bce9ab52d.banner2.jpg', 0, 0, '2024-04-08 00:54:54.211+06');
INSERT INTO public.anime VALUES (11, 'name3', 10, 'completed', 'studio', 'ona', 'source', '208668e8-166b-453c-9095-15802d1925d7.pic8.jpg', 'a49105c2-a45e-445f-8eee-7b9f08936cc6.banner2.jpg', 0, 0, '2024-04-08 03:12:24.554+06');


--
-- TOC entry 4830 (class 0 OID 24619)
-- Dependencies: 217
-- Data for Name: animecomment; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4832 (class 0 OID 24623)
-- Dependencies: 219
-- Data for Name: animegenre; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4834 (class 0 OID 24627)
-- Dependencies: 221
-- Data for Name: genre; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4838 (class 0 OID 24635)
-- Dependencies: 225
-- Data for Name: userlist; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4836 (class 0 OID 24631)
-- Dependencies: 223
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES (27, 'meow', '123456', 'abuallaban2002@bk.ru', NULL, '2024-04-07 00:52:30.269+06', 'user');
INSERT INTO public.users VALUES (26, 'aha1', '123456', 'ahmad@mail.ru', NULL, '2024-04-05 04:44:12.814+06', 'admin');
INSERT INTO public.users VALUES (28, 'aisana200250@gmail.com', 'aisana12345', 'aisana200250@gmail.com', NULL, '2024-04-07 02:10:30.57+06', 'user');


--
-- TOC entry 4851 (class 0 OID 0)
-- Dependencies: 216
-- Name: anime_anime_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.anime_anime_id_seq', 11, true);


--
-- TOC entry 4852 (class 0 OID 0)
-- Dependencies: 218
-- Name: animecomments_animecomment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animecomments_animecomment_id_seq', 1, false);


--
-- TOC entry 4853 (class 0 OID 0)
-- Dependencies: 220
-- Name: animegenres_animegenre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animegenres_animegenre_id_seq', 1, false);


--
-- TOC entry 4854 (class 0 OID 0)
-- Dependencies: 222
-- Name: genres_genre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.genres_genre_id_seq', 1, false);


--
-- TOC entry 4855 (class 0 OID 0)
-- Dependencies: 224
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 28, true);


--
-- TOC entry 4856 (class 0 OID 0)
-- Dependencies: 226
-- Name: userslist_userlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.userslist_userlist_id_seq', 1, false);


--
-- TOC entry 4666 (class 2606 OID 24646)
-- Name: anime anime_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.anime
    ADD CONSTRAINT anime_pkey PRIMARY KEY (anime_id);


--
-- TOC entry 4668 (class 2606 OID 24648)
-- Name: animecomment animecomments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animecomment
    ADD CONSTRAINT animecomments_pkey PRIMARY KEY (animecomment_id);


--
-- TOC entry 4670 (class 2606 OID 24650)
-- Name: animegenre animegenres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animegenre
    ADD CONSTRAINT animegenres_pkey PRIMARY KEY (animegenre_id);


--
-- TOC entry 4672 (class 2606 OID 24652)
-- Name: genre genres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genres_pkey PRIMARY KEY (genre_id);


--
-- TOC entry 4674 (class 2606 OID 24654)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 4676 (class 2606 OID 24656)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4678 (class 2606 OID 24658)
-- Name: userlist userslist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userlist
    ADD CONSTRAINT userslist_pkey PRIMARY KEY (userlist_id);


--
-- TOC entry 4679 (class 2606 OID 24659)
-- Name: animecomment animecomments_anime_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animecomment
    ADD CONSTRAINT animecomments_anime_id_fkey FOREIGN KEY (anime_id) REFERENCES public.anime(anime_id);


--
-- TOC entry 4680 (class 2606 OID 24664)
-- Name: animecomment animecomments_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animecomment
    ADD CONSTRAINT animecomments_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4681 (class 2606 OID 24669)
-- Name: animegenre animegenres_anime_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animegenre
    ADD CONSTRAINT animegenres_anime_id_fkey FOREIGN KEY (anime_id) REFERENCES public.anime(anime_id);


--
-- TOC entry 4682 (class 2606 OID 24674)
-- Name: animegenre animegenres_genre_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animegenre
    ADD CONSTRAINT animegenres_genre_id_fkey FOREIGN KEY (genre_id) REFERENCES public.genre(genre_id);


--
-- TOC entry 4683 (class 2606 OID 24679)
-- Name: userlist userslist_anime_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userlist
    ADD CONSTRAINT userslist_anime_id_fkey FOREIGN KEY (anime_id) REFERENCES public.anime(anime_id);


--
-- TOC entry 4684 (class 2606 OID 24684)
-- Name: userlist userslist_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userlist
    ADD CONSTRAINT userslist_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


-- Completed on 2024-04-08 14:47:42

--
-- PostgreSQL database dump complete
--

