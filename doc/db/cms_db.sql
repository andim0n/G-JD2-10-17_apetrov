CREATE TABLE "site" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"base_template" character varying NOT NULL,
	"menu" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT site_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "page" (
	"site_id" integer NOT NULL,
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"path" character varying NOT NULL,
	"template" character varying NOT NULL,
	"status" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	"homepage" BOOLEAN,
	CONSTRAINT page_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "block" (
	"id" serial NOT NULL,
	"page_id" integer NOT NULL,
	"number" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT block_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "content" (
	"id" serial NOT NULL,
	"block_id" integer NOT NULL,
	"number" integer NOT NULL,
	"html" TEXT NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT content_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "menu_item" (
	"id" serial NOT NULL,
	"page_id" integer NOT NULL,
	"site_id" integer NOT NULL,
	"name" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT menu_item_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_account" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"mail" character varying NOT NULL,
	"password" character varying NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT user_account_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "role_on_site" (
	"id" serial NOT NULL,
	"role" character varying NOT NULL,
	"user_account_id" integer NOT NULL,
	"site_id" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT role_on_site_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "change_history" (
	"id" serial NOT NULL,
	"page_id" integer NOT NULL,
	"user_account_id" integer NOT NULL,
	"comment" TEXT NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT change_history_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);




ALTER TABLE "page" ADD CONSTRAINT "page_fk0" FOREIGN KEY ("site_id") REFERENCES "site"("id");

ALTER TABLE "block" ADD CONSTRAINT "block_fk0" FOREIGN KEY ("page_id") REFERENCES "page"("id");

ALTER TABLE "content" ADD CONSTRAINT "content_fk0" FOREIGN KEY ("block_id") REFERENCES "block"("id");

ALTER TABLE "menu_item" ADD CONSTRAINT "menu_item_fk0" FOREIGN KEY ("page_id") REFERENCES "page"("id");
ALTER TABLE "menu_item" ADD CONSTRAINT "menu_item_fk1" FOREIGN KEY ("site_id") REFERENCES "site"("id");


ALTER TABLE "role_on_site" ADD CONSTRAINT "role_on_site_fk0" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");
ALTER TABLE "role_on_site" ADD CONSTRAINT "role_on_site_fk1" FOREIGN KEY ("site_id") REFERENCES "site"("id");

ALTER TABLE "change_history" ADD CONSTRAINT "change_history_fk0" FOREIGN KEY ("page_id") REFERENCES "page"("id");
ALTER TABLE "change_history" ADD CONSTRAINT "change_history_fk1" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");
