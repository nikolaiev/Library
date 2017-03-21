--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.5
-- Dumped by pg_dump version 9.5.5

-- Started on 2017-03-21 21:19:31 EET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12395)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2199 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 16815)
-- Name: author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE author (
    id integer NOT NULL,
    name character varying NOT NULL,
    soname character varying NOT NULL
);


ALTER TABLE author OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16813)
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
-- TOC entry 2200 (class 0 OID 0)
-- Dependencies: 181
-- Name: author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE author_id_seq OWNED BY author.id;


--
-- TOC entry 186 (class 1259 OID 16850)
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE book (
    id integer NOT NULL,
    aid bigint NOT NULL,
    pid bigint NOT NULL,
    genre character varying NOT NULL,
    lang character varying NOT NULL,
    pdate timestamp without time zone DEFAULT NOW()
);


ALTER TABLE book OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16848)
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
-- TOC entry 2201 (class 0 OID 0)
-- Dependencies: 185
-- Name: book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE book_id_seq OWNED BY book.id;


--
-- TOC entry 190 (class 1259 OID 16906)
-- Name: order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "order" (
    id integer NOT NULL,
    uid bigint NOT NULL,
    bid bigint NOT NULL,
    status character varying NOT NULL,
    type character varying NOT NULL,
    cdate timestamp without time zone DEFAULT now()
);


ALTER TABLE "order" OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16904)
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
-- TOC entry 2202 (class 0 OID 0)
-- Dependencies: 189
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE order_id_seq OWNED BY "order".id;


--
-- TOC entry 184 (class 1259 OID 16839)
-- Name: publisher; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE publisher (
    id integer NOT NULL,
    title character varying NOT NULL
);


ALTER TABLE publisher OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16837)
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
-- TOC entry 2203 (class 0 OID 0)
-- Dependencies: 183
-- Name: publisher_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE publisher_id_seq OWNED BY publisher.id;


--
-- TOC entry 188 (class 1259 OID 16872)
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
-- TOC entry 187 (class 1259 OID 16870)
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
-- TOC entry 2204 (class 0 OID 0)
-- Dependencies: 187
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- TOC entry 2048 (class 2604 OID 16818)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY author ALTER COLUMN id SET DEFAULT nextval('author_id_seq'::regclass);


--
-- TOC entry 2050 (class 2604 OID 16853)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY book ALTER COLUMN id SET DEFAULT nextval('book_id_seq'::regclass);


--
-- TOC entry 2052 (class 2604 OID 16909)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "order" ALTER COLUMN id SET DEFAULT nextval('order_id_seq'::regclass);


--
-- TOC entry 2049 (class 2604 OID 16842)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY publisher ALTER COLUMN id SET DEFAULT nextval('publisher_id_seq'::regclass);


--
-- TOC entry 2051 (class 2604 OID 16875)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- TOC entry 2183 (class 0 OID 16815)
-- Dependencies: 182
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY author (id, name, soname) FROM stdin;
\.


--
-- TOC entry 2205 (class 0 OID 0)
-- Dependencies: 181
-- Name: author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('author_id_seq', 1, false);


--
-- TOC entry 2187 (class 0 OID 16850)
-- Dependencies: 186
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY book (id, aid, pid, genre, lang, pdate) FROM stdin;
\.


--
-- TOC entry 2206 (class 0 OID 0)
-- Dependencies: 185
-- Name: book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('book_id_seq', 1, false);


--
-- TOC entry 2191 (class 0 OID 16906)
-- Dependencies: 190
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "order" (id, uid, bid, status, type, cdate) FROM stdin;
\.


--
-- TOC entry 2207 (class 0 OID 0)
-- Dependencies: 189
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('order_id_seq', 1, false);


--
-- TOC entry 2185 (class 0 OID 16839)
-- Dependencies: 184
-- Data for Name: publisher; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY publisher (id, title) FROM stdin;
\.


--
-- TOC entry 2208 (class 0 OID 0)
-- Dependencies: 183
-- Name: publisher_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('publisher_id_seq', 1, false);


--
-- TOC entry 2189 (class 0 OID 16872)
-- Dependencies: 188
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "user" (id, name, soname, login, pass, role) FROM stdin;
\.


--
-- TOC entry 2209 (class 0 OID 0)
-- Dependencies: 187
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 1, false);


--
-- TOC entry 2055 (class 2606 OID 16823)
-- Name: author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);


--
-- TOC entry 2059 (class 2606 OID 16858)
-- Name: book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);


--
-- TOC entry 2063 (class 2606 OID 16915)
-- Name: order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);


--
-- TOC entry 2057 (class 2606 OID 16847)
-- Name: publisher_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY publisher
    ADD CONSTRAINT publisher_pkey PRIMARY KEY (id);


--
-- TOC entry 2061 (class 2606 OID 16880)
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2064 (class 2606 OID 16859)
-- Name: book_aid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_aid_fkey FOREIGN KEY (aid) REFERENCES author(id);


--
-- TOC entry 2065 (class 2606 OID 16864)
-- Name: book_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_pid_fkey FOREIGN KEY (pid) REFERENCES publisher(id);


--
-- TOC entry 2067 (class 2606 OID 16921)
-- Name: order_bid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_bid_fkey FOREIGN KEY (bid) REFERENCES book(id);


--
-- TOC entry 2066 (class 2606 OID 16916)
-- Name: order_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_uid_fkey FOREIGN KEY (uid) REFERENCES "user"(id);


--
-- TOC entry 2198 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-03-21 21:19:31 EET

--
-- PostgreSQL database dump complete
--

