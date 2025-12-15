--
-- PostgreSQL database dump
--

\restrict Vm5SredPF08SfPmWXMWts77hMjnxSjcAhtedvQaGaq9ViTkj3h0DFjbvDcZgUQV

-- Dumped from database version 16.11 (Ubuntu 16.11-1.pgdg24.04+1)
-- Dumped by pg_dump version 18.1 (Ubuntu 18.1-1.pgdg24.04+2)

-- Started on 2025-12-15 10:14:01 -05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3587 (class 1262 OID 31357)
-- Name: pruebaTecnica; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "pruebaTecnica" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';


ALTER DATABASE "pruebaTecnica" OWNER TO postgres;

\unrestrict Vm5SredPF08SfPmWXMWts77hMjnxSjcAhtedvQaGaq9ViTkj3h0DFjbvDcZgUQV
\connect "pruebaTecnica"
\restrict Vm5SredPF08SfPmWXMWts77hMjnxSjcAhtedvQaGaq9ViTkj3h0DFjbvDcZgUQV

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- TOC entry 232 (class 1259 OID 31490)
-- Name: archivo_adjunto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.archivo_adjunto (
    id bigint NOT NULL,
    mime character varying NOT NULL,
    nombre_archivo character varying NOT NULL,
    fecha_creacion timestamp without time zone DEFAULT now() NOT NULL,
    tamano character varying NOT NULL,
    tramite_id bigint NOT NULL,
    estado_id bigint DEFAULT 1 NOT NULL
);


ALTER TABLE public.archivo_adjunto OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 31489)
-- Name: archivo_adjunto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.archivo_adjunto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.archivo_adjunto_id_seq OWNER TO postgres;

--
-- TOC entry 3588 (class 0 OID 0)
-- Dependencies: 231
-- Name: archivo_adjunto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.archivo_adjunto_id_seq OWNED BY public.archivo_adjunto.id;


--
-- TOC entry 234 (class 1259 OID 39458)
-- Name: comentario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comentario (
    id bigint NOT NULL,
    mensaje character varying NOT NULL,
    usuario_id bigint NOT NULL,
    tramite_id bigint NOT NULL,
    estado_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.comentario OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 39457)
-- Name: comentario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comentario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.comentario_id_seq OWNER TO postgres;

--
-- TOC entry 3589 (class 0 OID 0)
-- Dependencies: 233
-- Name: comentario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comentario_id_seq OWNED BY public.comentario.id;


--
-- TOC entry 238 (class 1259 OID 39501)
-- Name: documento_tipo_tramite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.documento_tipo_tramite (
    id bigint NOT NULL,
    tipo_tramite_id bigint NOT NULL,
    tipo_documento_id bigint NOT NULL,
    estado_id bigint NOT NULL
);


ALTER TABLE public.documento_tipo_tramite OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 39500)
-- Name: documento_tipo_tramite_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.documento_tipo_tramite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.documento_tipo_tramite_id_seq OWNER TO postgres;

--
-- TOC entry 3590 (class 0 OID 0)
-- Dependencies: 237
-- Name: documento_tipo_tramite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.documento_tipo_tramite_id_seq OWNED BY public.documento_tipo_tramite.id;


--
-- TOC entry 218 (class 1259 OID 31368)
-- Name: estado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estado (
    id bigint NOT NULL,
    nombre character varying NOT NULL
);


ALTER TABLE public.estado OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 31367)
-- Name: estado_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.estado_id_seq OWNER TO postgres;

--
-- TOC entry 3591 (class 0 OID 0)
-- Dependencies: 217
-- Name: estado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estado_id_seq OWNED BY public.estado.id;


--
-- TOC entry 222 (class 1259 OID 31396)
-- Name: persona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persona (
    id bigint NOT NULL,
    nombre character varying NOT NULL,
    segundo_nombre character varying,
    apellido character varying NOT NULL,
    segundo_apellido character varying,
    correo character varying NOT NULL,
    numero_identificacion character varying NOT NULL,
    tipo_identificacion_id bigint NOT NULL,
    estado_id bigint DEFAULT 1 NOT NULL
);


ALTER TABLE public.persona OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 31395)
-- Name: persona_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.persona_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.persona_id_seq OWNER TO postgres;

--
-- TOC entry 3592 (class 0 OID 0)
-- Dependencies: 221
-- Name: persona_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.persona_id_seq OWNED BY public.persona.id;


--
-- TOC entry 220 (class 1259 OID 31377)
-- Name: rol; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rol (
    id bigint NOT NULL,
    nombre character varying NOT NULL,
    estado_id bigint DEFAULT 1 NOT NULL
);


ALTER TABLE public.rol OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 31376)
-- Name: rol_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rol_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rol_id_seq OWNER TO postgres;

--
-- TOC entry 3593 (class 0 OID 0)
-- Dependencies: 219
-- Name: rol_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rol_id_seq OWNED BY public.rol.id;


--
-- TOC entry 239 (class 1259 OID 39531)
-- Name: seguimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.seguimiento (
    id bigint NOT NULL,
    tramite_id bigint NOT NULL,
    usuario_id bigint NOT NULL,
    estado_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.seguimiento OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 39542)
-- Name: seguimiento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seguimiento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seguimiento_id_seq OWNER TO postgres;

--
-- TOC entry 3594 (class 0 OID 0)
-- Dependencies: 240
-- Name: seguimiento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.seguimiento_id_seq OWNED BY public.seguimiento.id;


--
-- TOC entry 236 (class 1259 OID 39483)
-- Name: tipo_documento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_documento (
    id bigint NOT NULL,
    nombre character varying NOT NULL,
    estado_id bigint NOT NULL
);


ALTER TABLE public.tipo_documento OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 39482)
-- Name: tipo_documento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_documento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipo_documento_id_seq OWNER TO postgres;

--
-- TOC entry 3595 (class 0 OID 0)
-- Dependencies: 235
-- Name: tipo_documento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_documento_id_seq OWNED BY public.tipo_documento.id;


--
-- TOC entry 224 (class 1259 OID 31413)
-- Name: tipo_identificacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_identificacion (
    id bigint NOT NULL,
    nombre character varying NOT NULL,
    estado_id bigint DEFAULT 1 NOT NULL
);


ALTER TABLE public.tipo_identificacion OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 31412)
-- Name: tipo_identificacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_identificacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipo_identificacion_id_seq OWNER TO postgres;

--
-- TOC entry 3596 (class 0 OID 0)
-- Dependencies: 223
-- Name: tipo_identificacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_identificacion_id_seq OWNED BY public.tipo_identificacion.id;


--
-- TOC entry 228 (class 1259 OID 31454)
-- Name: tipo_tramite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_tramite (
    id bigint NOT NULL,
    nombre character varying NOT NULL,
    estado_id bigint DEFAULT 1 NOT NULL,
    descripcion character varying(1000)
);


ALTER TABLE public.tipo_tramite OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 31453)
-- Name: tipo_tramite_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_tramite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipo_tramite_id_seq OWNER TO postgres;

--
-- TOC entry 3597 (class 0 OID 0)
-- Dependencies: 227
-- Name: tipo_tramite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_tramite_id_seq OWNED BY public.tipo_tramite.id;


--
-- TOC entry 230 (class 1259 OID 31471)
-- Name: tramite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tramite (
    id bigint NOT NULL,
    descripcion character varying,
    tipo_tramite_id bigint NOT NULL,
    estado_id bigint DEFAULT 1 NOT NULL,
    asignado_id bigint,
    creador_id bigint
);


ALTER TABLE public.tramite OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 31470)
-- Name: tramite_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tramite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tramite_id_seq OWNER TO postgres;

--
-- TOC entry 3598 (class 0 OID 0)
-- Dependencies: 229
-- Name: tramite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tramite_id_seq OWNED BY public.tramite.id;


--
-- TOC entry 216 (class 1259 OID 31359)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    contrasena character varying NOT NULL,
    persona_id bigint NOT NULL,
    estado_id bigint DEFAULT 1 NOT NULL,
    nombre character varying NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 31358)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 3599 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- TOC entry 226 (class 1259 OID 31432)
-- Name: usuario_rol; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_rol (
    id bigint NOT NULL,
    usuario_id bigint NOT NULL,
    rol_id bigint NOT NULL,
    estado_id bigint DEFAULT 1 NOT NULL
);


ALTER TABLE public.usuario_rol OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 31431)
-- Name: usuario_rol_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_rol_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_rol_id_seq OWNER TO postgres;

--
-- TOC entry 3600 (class 0 OID 0)
-- Dependencies: 225
-- Name: usuario_rol_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_rol_id_seq OWNED BY public.usuario_rol.id;


--
-- TOC entry 3345 (class 2604 OID 31493)
-- Name: archivo_adjunto id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.archivo_adjunto ALTER COLUMN id SET DEFAULT nextval('public.archivo_adjunto_id_seq'::regclass);


--
-- TOC entry 3348 (class 2604 OID 39461)
-- Name: comentario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario ALTER COLUMN id SET DEFAULT nextval('public.comentario_id_seq'::regclass);


--
-- TOC entry 3351 (class 2604 OID 39504)
-- Name: documento_tipo_tramite id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documento_tipo_tramite ALTER COLUMN id SET DEFAULT nextval('public.documento_tipo_tramite_id_seq'::regclass);


--
-- TOC entry 3332 (class 2604 OID 31371)
-- Name: estado id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado ALTER COLUMN id SET DEFAULT nextval('public.estado_id_seq'::regclass);


--
-- TOC entry 3335 (class 2604 OID 31399)
-- Name: persona id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona ALTER COLUMN id SET DEFAULT nextval('public.persona_id_seq'::regclass);


--
-- TOC entry 3333 (class 2604 OID 31380)
-- Name: rol id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol ALTER COLUMN id SET DEFAULT nextval('public.rol_id_seq'::regclass);


--
-- TOC entry 3352 (class 2604 OID 39543)
-- Name: seguimiento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seguimiento ALTER COLUMN id SET DEFAULT nextval('public.seguimiento_id_seq'::regclass);


--
-- TOC entry 3350 (class 2604 OID 39486)
-- Name: tipo_documento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_documento ALTER COLUMN id SET DEFAULT nextval('public.tipo_documento_id_seq'::regclass);


--
-- TOC entry 3337 (class 2604 OID 31416)
-- Name: tipo_identificacion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_identificacion ALTER COLUMN id SET DEFAULT nextval('public.tipo_identificacion_id_seq'::regclass);


--
-- TOC entry 3341 (class 2604 OID 31457)
-- Name: tipo_tramite id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_tramite ALTER COLUMN id SET DEFAULT nextval('public.tipo_tramite_id_seq'::regclass);


--
-- TOC entry 3343 (class 2604 OID 31474)
-- Name: tramite id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tramite ALTER COLUMN id SET DEFAULT nextval('public.tramite_id_seq'::regclass);


--
-- TOC entry 3330 (class 2604 OID 31362)
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- TOC entry 3339 (class 2604 OID 31435)
-- Name: usuario_rol id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_rol ALTER COLUMN id SET DEFAULT nextval('public.usuario_rol_id_seq'::regclass);


--
-- TOC entry 3398 (class 2606 OID 39508)
-- Name: archivo_adjunto archivo_adjunto_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.archivo_adjunto
    ADD CONSTRAINT archivo_adjunto_id_key UNIQUE (id);


--
-- TOC entry 3400 (class 2606 OID 31497)
-- Name: archivo_adjunto archivo_adjunto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.archivo_adjunto
    ADD CONSTRAINT archivo_adjunto_pkey PRIMARY KEY (id);


--
-- TOC entry 3402 (class 2606 OID 39510)
-- Name: comentario comentario_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_id_key UNIQUE (id);


--
-- TOC entry 3404 (class 2606 OID 39465)
-- Name: comentario comentario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_pkey PRIMARY KEY (id);


--
-- TOC entry 3358 (class 2606 OID 39541)
-- Name: archivo_adjunto comprobar_validez_archivo_adjunto; Type: CHECK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE public.archivo_adjunto
    ADD CONSTRAINT comprobar_validez_archivo_adjunto CHECK ((estado_id = ANY (ARRAY[(1)::bigint, (2)::bigint]))) NOT VALID;


--
-- TOC entry 3359 (class 2606 OID 39540)
-- Name: comentario comprobar_validez_comentario; Type: CHECK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE public.comentario
    ADD CONSTRAINT comprobar_validez_comentario CHECK ((estado_id = ANY (ARRAY[(1)::bigint, (2)::bigint]))) NOT VALID;


--
-- TOC entry 3356 (class 2606 OID 39539)
-- Name: persona comprobar_validez_persona; Type: CHECK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE public.persona
    ADD CONSTRAINT comprobar_validez_persona CHECK ((estado_id = ANY (ARRAY[(1)::bigint, (2)::bigint]))) NOT VALID;


--
-- TOC entry 3355 (class 2606 OID 39538)
-- Name: rol comprobar_validez_rol; Type: CHECK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE public.rol
    ADD CONSTRAINT comprobar_validez_rol CHECK ((estado_id = ANY (ARRAY[(1)::bigint, (2)::bigint]))) NOT VALID;


--
-- TOC entry 3360 (class 2606 OID 39537)
-- Name: tipo_documento comprobar_validez_tipo_documento; Type: CHECK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE public.tipo_documento
    ADD CONSTRAINT comprobar_validez_tipo_documento CHECK ((estado_id = ANY (ARRAY[(1)::bigint, (2)::bigint]))) NOT VALID;


--
-- TOC entry 3357 (class 2606 OID 39536)
-- Name: usuario_rol comprobar_validez_usuario_rol; Type: CHECK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE public.usuario_rol
    ADD CONSTRAINT comprobar_validez_usuario_rol CHECK ((estado_id = ANY (ARRAY[(1)::bigint, (2)::bigint]))) NOT VALID;


--
-- TOC entry 3354 (class 2606 OID 39535)
-- Name: usuario comprobar_validez_usuarios; Type: CHECK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE public.usuario
    ADD CONSTRAINT comprobar_validez_usuarios CHECK ((estado_id = ANY (ARRAY[(1)::bigint, (2)::bigint]))) NOT VALID;


--
-- TOC entry 3412 (class 2606 OID 39506)
-- Name: documento_tipo_tramite documento_tipo_tramite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documento_tipo_tramite
    ADD CONSTRAINT documento_tipo_tramite_pkey PRIMARY KEY (id);


--
-- TOC entry 3368 (class 2606 OID 39512)
-- Name: estado estado_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado
    ADD CONSTRAINT estado_id_key UNIQUE (id);


--
-- TOC entry 3370 (class 2606 OID 31375)
-- Name: estado estado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);


--
-- TOC entry 3376 (class 2606 OID 39516)
-- Name: persona persona_correo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_correo_key UNIQUE (correo);


--
-- TOC entry 3378 (class 2606 OID 39518)
-- Name: persona persona_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_id_key UNIQUE (id);


--
-- TOC entry 3380 (class 2606 OID 39514)
-- Name: persona persona_numero_identificacion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_numero_identificacion_key UNIQUE (numero_identificacion);


--
-- TOC entry 3382 (class 2606 OID 31404)
-- Name: persona persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id);


--
-- TOC entry 3372 (class 2606 OID 39520)
-- Name: rol rol_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_id_key UNIQUE (id);


--
-- TOC entry 3374 (class 2606 OID 31384)
-- Name: rol rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id);


--
-- TOC entry 3414 (class 2606 OID 39566)
-- Name: seguimiento seguimiento_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seguimiento
    ADD CONSTRAINT seguimiento_id_key UNIQUE (id);


--
-- TOC entry 3416 (class 2606 OID 39549)
-- Name: seguimiento seguimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seguimiento
    ADD CONSTRAINT seguimiento_pkey PRIMARY KEY (id);


--
-- TOC entry 3406 (class 2606 OID 39499)
-- Name: tipo_documento tipo_documento_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_documento
    ADD CONSTRAINT tipo_documento_id_key UNIQUE (id);


--
-- TOC entry 3408 (class 2606 OID 39492)
-- Name: tipo_documento tipo_documento_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_documento
    ADD CONSTRAINT tipo_documento_nombre_key UNIQUE (nombre);


--
-- TOC entry 3410 (class 2606 OID 39490)
-- Name: tipo_documento tipo_documento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_documento
    ADD CONSTRAINT tipo_documento_pkey PRIMARY KEY (id);


--
-- TOC entry 3384 (class 2606 OID 39522)
-- Name: tipo_identificacion tipo_identificacion_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_identificacion
    ADD CONSTRAINT tipo_identificacion_id_key UNIQUE (id);


--
-- TOC entry 3386 (class 2606 OID 31420)
-- Name: tipo_identificacion tipo_identificacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_identificacion
    ADD CONSTRAINT tipo_identificacion_pkey PRIMARY KEY (id);


--
-- TOC entry 3390 (class 2606 OID 39524)
-- Name: tipo_tramite tipo_tramite_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_tramite
    ADD CONSTRAINT tipo_tramite_id_key UNIQUE (id);


--
-- TOC entry 3392 (class 2606 OID 31462)
-- Name: tipo_tramite tipo_tramite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_tramite
    ADD CONSTRAINT tipo_tramite_pkey PRIMARY KEY (id);


--
-- TOC entry 3394 (class 2606 OID 39526)
-- Name: tramite tramite_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tramite
    ADD CONSTRAINT tramite_id_key UNIQUE (id);


--
-- TOC entry 3396 (class 2606 OID 31478)
-- Name: tramite tramite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tramite
    ADD CONSTRAINT tramite_pkey PRIMARY KEY (id);


--
-- TOC entry 3362 (class 2606 OID 39530)
-- Name: usuario usuario_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_key UNIQUE (id);


--
-- TOC entry 3364 (class 2606 OID 39528)
-- Name: usuario usuario_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_nombre_key UNIQUE (nombre);


--
-- TOC entry 3366 (class 2606 OID 31366)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 3388 (class 2606 OID 31437)
-- Name: usuario_rol usuario_rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_pkey PRIMARY KEY (id);


--
-- TOC entry 3430 (class 2606 OID 31503)
-- Name: archivo_adjunto archivo_adjunto_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.archivo_adjunto
    ADD CONSTRAINT archivo_adjunto_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estado(id);


--
-- TOC entry 3431 (class 2606 OID 31498)
-- Name: archivo_adjunto archivo_adjunto_tramite_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.archivo_adjunto
    ADD CONSTRAINT archivo_adjunto_tramite_id_fkey FOREIGN KEY (tramite_id) REFERENCES public.tramite(id);


--
-- TOC entry 3432 (class 2606 OID 39476)
-- Name: comentario comentario_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estado(id);


--
-- TOC entry 3433 (class 2606 OID 39471)
-- Name: comentario comentario_tramite_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_tramite_id_fkey FOREIGN KEY (tramite_id) REFERENCES public.tramite(id);


--
-- TOC entry 3434 (class 2606 OID 39466)
-- Name: comentario comentario_usuario_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_usuario_id_fkey FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- TOC entry 3419 (class 2606 OID 31405)
-- Name: persona persona_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estado(id);


--
-- TOC entry 3420 (class 2606 OID 31426)
-- Name: persona persona_tipo_identificacion_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_tipo_identificacion_id_fkey FOREIGN KEY (tipo_identificacion_id) REFERENCES public.tipo_identificacion(id) NOT VALID;


--
-- TOC entry 3418 (class 2606 OID 31385)
-- Name: rol rol_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estado(id);


--
-- TOC entry 3436 (class 2606 OID 39560)
-- Name: seguimiento seguimiento_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seguimiento
    ADD CONSTRAINT seguimiento_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estado(id) NOT VALID;


--
-- TOC entry 3437 (class 2606 OID 39550)
-- Name: seguimiento seguimiento_tramite_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seguimiento
    ADD CONSTRAINT seguimiento_tramite_id_fkey FOREIGN KEY (tramite_id) REFERENCES public.tramite(id) NOT VALID;


--
-- TOC entry 3438 (class 2606 OID 39555)
-- Name: seguimiento seguimiento_usuario_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seguimiento
    ADD CONSTRAINT seguimiento_usuario_id_fkey FOREIGN KEY (usuario_id) REFERENCES public.usuario(id) NOT VALID;


--
-- TOC entry 3435 (class 2606 OID 39493)
-- Name: tipo_documento tipo_documento_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_documento
    ADD CONSTRAINT tipo_documento_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estado(id);


--
-- TOC entry 3421 (class 2606 OID 31421)
-- Name: tipo_identificacion tipo_identificacion_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_identificacion
    ADD CONSTRAINT tipo_identificacion_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estado(id);


--
-- TOC entry 3425 (class 2606 OID 31463)
-- Name: tipo_tramite tipo_tramite_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_tramite
    ADD CONSTRAINT tipo_tramite_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estado(id);


--
-- TOC entry 3426 (class 2606 OID 39452)
-- Name: tramite tramite_creador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tramite
    ADD CONSTRAINT tramite_creador_fkey FOREIGN KEY (creador_id) REFERENCES public.usuario(id) NOT VALID;


--
-- TOC entry 3427 (class 2606 OID 31484)
-- Name: tramite tramite_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tramite
    ADD CONSTRAINT tramite_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estado(id);


--
-- TOC entry 3428 (class 2606 OID 31479)
-- Name: tramite tramite_tipo_tramite_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tramite
    ADD CONSTRAINT tramite_tipo_tramite_id_fkey FOREIGN KEY (tipo_tramite_id) REFERENCES public.tipo_tramite(id);


--
-- TOC entry 3429 (class 2606 OID 39447)
-- Name: tramite tramite_usuario_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tramite
    ADD CONSTRAINT tramite_usuario_id_fkey FOREIGN KEY (asignado_id) REFERENCES public.usuario(id) NOT VALID;


--
-- TOC entry 3417 (class 2606 OID 31390)
-- Name: usuario usuario_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estado(id) NOT VALID;


--
-- TOC entry 3422 (class 2606 OID 31448)
-- Name: usuario_rol usuario_rol_estado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES public.estado(id);


--
-- TOC entry 3423 (class 2606 OID 31443)
-- Name: usuario_rol usuario_rol_rol_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_rol_id_fkey FOREIGN KEY (rol_id) REFERENCES public.rol(id);


--
-- TOC entry 3424 (class 2606 OID 31438)
-- Name: usuario_rol usuario_rol_usuario_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_usuario_id_fkey FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


-- Completed on 2025-12-15 10:14:01 -05

--
-- PostgreSQL database dump complete
--

\unrestrict Vm5SredPF08SfPmWXMWts77hMjnxSjcAhtedvQaGaq9ViTkj3h0DFjbvDcZgUQV

