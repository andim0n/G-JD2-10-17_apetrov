CREATE TABLE "post" (
	"id" serial NOT NULL,
	"type" varchar NOT NULL,
	"name" varchar NOT NULL,
	"user_id" integer NOT NULL,
	"create" TIMESTAMP NOT NULL,
	"update" TIMESTAMP NOT NULL,
	"title" varchar NOT NULL,
	"content" TEXT NOT NULL,
	"status" varchar NOT NULL,
	"comment_status" varchar NOT NULL,
	"category_id" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT post_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"password" varchar NOT NULL,
	"admin" BOOLEAN NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "category" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"visible" BOOLEAN NOT NULL,
	"parent_id" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT category_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "comment" (
	"id" serial NOT NULL,
	"post_id" integer NOT NULL,
	"user_id" integer NOT NULL,
	"ip" varchar NOT NULL,
	"date" TIMESTAMP NOT NULL,
	"content" TEXT NOT NULL,
	"approved" BOOLEAN NOT NULL,
	"rating" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT comment_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "property" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"value" varchar NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT property_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "link" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"post_id" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT link_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "post" ADD CONSTRAINT "post_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");
ALTER TABLE "post" ADD CONSTRAINT "post_fk1" FOREIGN KEY ("category_id") REFERENCES "category"("id");


ALTER TABLE "category" ADD CONSTRAINT "category_fk0" FOREIGN KEY ("parent_id") REFERENCES "category"("id");

ALTER TABLE "comment" ADD CONSTRAINT "comment_fk0" FOREIGN KEY ("post_id") REFERENCES "post"("id");
ALTER TABLE "comment" ADD CONSTRAINT "comment_fk1" FOREIGN KEY ("user_id") REFERENCES "user"("id");


ALTER TABLE "link" ADD CONSTRAINT "link_fk0" FOREIGN KEY ("post_id") REFERENCES "post"("id");
