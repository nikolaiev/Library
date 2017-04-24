--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.5.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE author (
    id integer NOT NULL,
    name character varying NOT NULL,
    soname character varying NOT NULL
);


ALTER TABLE author OWNER TO postgres;

--
-- Name: author_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE author_id_seq OWNER TO postgres;

--
-- Name: author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE author_id_seq OWNED BY author.id;


--
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE book (
    id integer NOT NULL,
    aid bigint NOT NULL,
    pid bigint NOT NULL,
    genre character varying NOT NULL,
    lang character varying NOT NULL,
    pdate date DEFAULT now() NOT NULL,
    title character varying NOT NULL,
    count integer NOT NULL,
    count_in_use integer DEFAULT 0 NOT NULL,
    image character varying(256) DEFAULT 'defaultImage.jpg'::character varying NOT NULL
);


ALTER TABLE book OWNER TO postgres;

--
-- Name: publisher; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE publisher (
    id integer NOT NULL,
    title character varying NOT NULL
);


ALTER TABLE publisher OWNER TO postgres;

--
-- Name: book_full_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW book_full_view AS
 SELECT b.id,
    b.aid AS author_id,
    b.pid AS publisher_id,
    b.genre,
    b.lang,
    b.pdate,
    p.title AS publisher_title,
    a.name AS author_name,
    a.soname AS author_soname,
    b.title,
    b.count,
    b.image
   FROM ((book b
     LEFT JOIN publisher p ON ((b.pid = p.id)))
     LEFT JOIN author a ON ((b.aid = a.id)));


ALTER TABLE book_full_view OWNER TO postgres;

--
-- Name: book_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE book_id_seq OWNER TO postgres;

--
-- Name: book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE book_id_seq OWNED BY book.id;


--
-- Name: order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "order" (
    id integer NOT NULL,
    uid integer NOT NULL,
    bid integer NOT NULL,
    type character varying NOT NULL,
    status character varying NOT NULL,
    cdate timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE "order" OWNER TO postgres;

--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "user" (
    id integer NOT NULL,
    name character varying NOT NULL,
    soname character varying NOT NULL,
    login character varying NOT NULL,
    pass character varying NOT NULL,
    role character varying NOT NULL
);


ALTER TABLE "user" OWNER TO postgres;

--
-- Name: order_full_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW order_full_view AS
 SELECT o.id AS ord_id,
    o.uid,
    o.bid,
    o.type,
    o.status,
    o.cdate,
    u.name,
    u.soname,
    u.login,
    u.pass,
    u.role,
    b.author_id,
    b.publisher_id,
    b.genre,
    b.lang,
    b.pdate,
    b.publisher_title,
    b.author_name,
    b.author_soname,
    b.title,
    b.count
   FROM (("order" o
     LEFT JOIN "user" u ON ((o.uid = u.id)))
     LEFT JOIN book_full_view b ON ((o.bid = b.id)))
  ORDER BY o.cdate DESC;


ALTER TABLE order_full_view OWNER TO postgres;

--
-- Name: order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE order_id_seq OWNER TO postgres;

--
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE order_id_seq OWNED BY "order".id;


--
-- Name: publisher_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE publisher_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE publisher_id_seq OWNER TO postgres;

--
-- Name: publisher_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE publisher_id_seq OWNED BY publisher.id;


--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_id_seq OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY author ALTER COLUMN id SET DEFAULT nextval('author_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY book ALTER COLUMN id SET DEFAULT nextval('book_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "order" ALTER COLUMN id SET DEFAULT nextval('order_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY publisher ALTER COLUMN id SET DEFAULT nextval('publisher_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY author (id, name, soname) FROM stdin;
4	Vlad	Nikolaiev
6	Taras	Shevchenko
7	Olexandr	Pushkin
8	Wiliam	Shakespeare
30	GOD	test
\.


--
-- Name: author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('author_id_seq', 30, true);


--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY book (id, aid, pid, genre, lang, pdate, title, count, count_in_use, image) FROM stdin;
25	8	2	EDUCATION	ENG	2017-04-11	Romeo And Juliet	3	0	3369a11f_d8f7_4751_90ff_044d9461502d.jpg
15	4	4	EDUCATION	UA	2017-04-16	5 choises	1	0	48c181e9_edd9_4bee_93d9_bd4aaa0005c2.jpg
16	4	1	EDUCATION	ENG	2017-04-16	Redesign the WEB	6	1	ca199d58_6536_42ad_8dfb_d1889b5a1905.png
1	8	2	EDUCATION	UA	2017-03-23	My Super pooper book	10	1	defaultBook.jpg
17	4	2	EDUCATION	ENG	2017-04-05	new bok	3	2	36f2f24f_ca3e_4a75_8dd3_0984ceecdb11.png
10	6	2	KIDS	ENG	2017-03-24	Test book	2	2	defaultBook233.jpg
26	6	1	FANTASY	ENG	2017-04-05	test	2	2	e4a1baae_4bc6_4992_9398_1245566e511f.png
3	7	1	KIDS	RUS	2017-03-23	Kids tails	15	2	9df22843_4091_46ce_946d_09222b04fdfc.jpg
13	6	1	KIDS	RUS	2017-04-12	Book with no image	1	1	3cf371c0_0c46_4a08_96c9_7969173081dd.jpg
14	4	1	EDUCATION	ENG	2017-04-16	admin book	5	2	376ed989_5ba4_411d_bb1e_d7ec41b70133.jpg
27	7	1	DETECTIVE	RUS	2017-03-31	some book 1	0	0	730e781f_73fe_4efe_8914_da435ab6726b.jpg
\.


--
-- Name: book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('book_id_seq', 27, true);


--
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "order" (id, uid, bid, type, status, cdate) FROM stdin;
37	5	10	HOME	RETURNED	2017-04-12 03:17:03.273776
38	5	10	HOME	RETURNED	2017-04-12 03:17:12.43852
39	5	10	HOME	RETURNED	2017-04-12 03:17:18.710481
40	5	10	HOME	RETURNED	2017-04-12 03:17:20.034692
41	5	10	HOME	RETURNED	2017-04-12 03:22:50.87617
42	5	10	HOME	RETURNED	2017-04-12 03:22:51.877565
43	5	10	HOME	RETURNED	2017-04-12 03:22:52.33803
45	5	10	HOME	RETURNED	2017-04-12 03:23:30.381524
46	5	10	HOME	RETURNED	2017-04-12 03:23:31.641103
47	5	10	HOME	RETURNED	2017-04-12 03:23:32.050659
48	5	10	HOME	RETURNED	2017-04-12 03:26:33.078328
49	5	10	HOME	RETURNED	2017-04-12 03:26:31.92334
50	5	10	HOME	RETURNED	2017-04-12 03:26:31.92334
51	5	10	HOME	RETURNED	2017-04-12 03:35:35.486903
54	5	10	HOME	RETURNED	2017-04-12 13:06:10.199393
55	5	10	HOME	RETURNED	2017-04-12 13:07:51.638176
56	5	10	HOME	RETURNED	2017-04-12 13:07:53.931901
57	5	13	HOME	RETURNED	2017-04-16 04:40:05.526843
36	5	10	HOME	RETURNED	2017-04-12 03:11:06.065797
31	5	10	HOME	RETURNED	2017-04-12 02:46:45.77353
29	5	10	HOME	RETURNED	2017-04-12 02:46:38.052001
30	5	3	HOME	RETURNED	2017-04-12 02:46:45.767215
28	5	3	HOME	RETURNED	2017-04-12 02:46:38.04069
33	5	10	HOME	RETURNED	2017-04-12 02:56:42.352165
32	5	3	HOME	RETURNED	2017-04-12 02:56:42.346745
25	5	10	HOME	RETURNED	2017-04-11 23:16:00.154576
26	5	3	HOME	RETURNED	2017-04-11 23:23:13.382699
24	5	3	HOME	RETURNED	2017-04-11 23:15:56.823122
27	5	10	HOME	RETURNED	2017-04-11 23:23:13.398995
34	5	10	HOME	RETURNED	2017-04-12 03:09:04.415312
35	5	10	HOME	RETURNED	2017-04-12 03:08:52.933568
58	5	13	HOME	RETURNED	2017-04-16 04:57:52.277326
60	5	13	LIBRARY	RETURNED	2017-04-16 05:30:04.113644
59	5	10	HOME	RETURNED	2017-04-16 05:30:04.096289
61	5	13	LIBRARY	RETURNED	2017-04-16 05:40:42.44724
67	5	14	HOME	RETURNED	2017-04-18 03:10:19.131791
66	5	10	LIBRARY	RETURNED	2017-04-18 02:48:10.102875
65	5	3	LIBRARY	RETURNED	2017-04-18 02:45:14.671078
64	5	15	LIBRARY	RETURNED	2017-04-18 02:28:15.208719
63	5	1	LIBRARY	RETURNED	2017-04-18 01:32:18.275835
62	5	13	LIBRARY	RETURNED	2017-04-16 05:41:14.343279
68	5	16	HOME	GRANTED	2017-04-18 03:14:44.734412
69	5	17	HOME	GRANTED	2017-04-18 03:14:44.740131
70	5	13	HOME	GRANTED	2017-04-18 03:14:44.745585
71	5	14	LIBRARY	GRANTED	2017-04-19 07:23:42.162089
72	5	10	HOME	GRANTED	2017-04-19 13:42:28.942811
77	5	1	LIBRARY	RETURNED	2017-04-23 06:27:43.980421
76	5	1	LIBRARY	RETURNED	2017-04-23 03:08:40.011021
75	5	1	HOME	RETURNED	2017-04-21 14:00:00.099148
73	5	15	LIBRARY	RETURNED	2017-04-19 17:19:38.19094
74	5	16	LIBRARY	RETURNED	2017-04-21 14:00:00.085927
78	12	1	LIBRARY	GRANTED	2017-04-23 19:04:04.041586
79	12	3	HOME	GRANTED	2017-04-23 19:04:04.058177
80	12	26	HOME	GRANTED	2017-04-23 19:04:04.065515
81	5	16	HOME	GRANTED	2017-04-24 02:31:18.322115
82	5	1	HOME	GRANTED	2017-04-24 02:31:18.334043
83	5	17	HOME	GRANTED	2017-04-24 02:31:18.339448
84	5	3	HOME	GRANTED	2017-04-24 02:31:18.345771
85	5	10	HOME	GRANTED	2017-04-24 02:31:18.351092
86	5	26	HOME	GRANTED	2017-04-24 02:31:18.356641
87	5	14	HOME	GRANTED	2017-04-24 02:31:18.371261
\.


--
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('order_id_seq', 87, true);


--
-- Data for Name: publisher; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY publisher (id, title) FROM stdin;
1	O'Relly
2	Світанок
3	Москва
4	Технічна бібліотека КПІ
\.


--
-- Name: publisher_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('publisher_id_seq', 4, true);


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "user" (id, name, soname, login, pass, role) FROM stdin;
1	Vlad	Test	test@i.ua	admin	ADMIN
5	Test	Sotest	test2@i.ua	user	USER
6	test3	test3	test3@i.ua	1111	USER
7	1	2	i@i.ua	1	USER
8					USER
11	Test123	Test123Soname	test0@i.ua	test0	USER
12	test	test	test4@i.ua	1111	USER
\.


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 12, true);


--
-- Name: author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);


--
-- Name: book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);


--
-- Name: full_name_uniq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY author
    ADD CONSTRAINT full_name_uniq UNIQUE (name, soname);


--
-- Name: login_uniq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT login_uniq UNIQUE (login);


--
-- Name: order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);


--
-- Name: publisher_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY publisher
    ADD CONSTRAINT publisher_pkey PRIMARY KEY (id);


--
-- Name: title_uniq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY publisher
    ADD CONSTRAINT title_uniq UNIQUE (title);


--
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: book_aid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_aid_fkey FOREIGN KEY (aid) REFERENCES author(id);


--
-- Name: book_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_pid_fkey FOREIGN KEY (pid) REFERENCES publisher(id);


--
-- Name: order_bid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_bid_fkey FOREIGN KEY (bid) REFERENCES book(id);


--
-- Name: order_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_uid_fkey FOREIGN KEY (uid) REFERENCES "user"(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

