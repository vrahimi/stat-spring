/*
Created: 11/13/2020
Modified: 11/13/2020
Model: My SQL8
Database: MySQL 8.0
*/

-- Create tables section -------------------------------------------------

-- Table project

CREATE TABLE project
(
  project_id Int NOT NULL AUTO_INCREMENT,
  descr Varchar(100) NOT NULL,
  workflow_id Int,
  step_id Int NOT NULL,
  update_dt Datetime NOT NULL,
  owner_id Int NOT NULL,
  update_userid Int NOT NULL,
  PRIMARY KEY (project_id)
)
;

CREATE INDEX IX_Relationship9 ON project (owner_id)
;

CREATE INDEX IX_Relationship10 ON project (update_userid)
;

CREATE INDEX IX_Relationship12 ON project (workflow_id)
;

CREATE INDEX IX_Relationship13 ON project (step_id)
;

-- Table rd_object

CREATE TABLE rd_object
(
  objectid Int NOT NULL AUTO_INCREMENT,
  object_type_id Int NOT NULL,
  descr Varchar(300) NOT NULL,
  PRIMARY KEY (objectid)
)
;

-- Table project_object

CREATE TABLE project_object
(
  project_id Int NOT NULL,
  objectid Int NOT NULL
)
;

ALTER TABLE project_object ADD PRIMARY KEY (project_id, objectid)
;

-- Table project_archive

CREATE TABLE project_archive
(
  archive_id Int NOT NULL AUTO_INCREMENT,
  project_id Int NOT NULL,
  env_id Int NOT NULL,
  PRIMARY KEY (archive_id)
)
;

CREATE INDEX IX_Relationship5 ON project_archive (project_id)
;

CREATE INDEX IX_Relationship8 ON project_archive (env_id)
;

-- Table project_archive_object

CREATE TABLE project_archive_object
(
  archive_id Int NOT NULL,
  objectid Int NOT NULL
)
;

ALTER TABLE project_archive_object ADD PRIMARY KEY (archive_id, objectid)
;

-- Table rd_environment

CREATE TABLE rd_environment
(
  env_id Int NOT NULL AUTO_INCREMENT,
  descr Varchar(50) NOT NULL,
  status_cd Char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (env_id)
)
;

-- Table rd_user

CREATE TABLE rd_user
(
  user_id Int NOT NULL AUTO_INCREMENT,
  user_code varchar(50) NOt NULL,
  last_name Varchar(30) NOT NULL,
  first_name Varchar(30) NOT NULL,
  status_cd Char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (user_id)
)
;

CREATE INDEX user_code_idx1 ON rd_user (user_code);

-- Table rd_workflow

CREATE TABLE rd_workflow
(
  workflow_id Int NOT NULL AUTO_INCREMENT,
  descr Varchar(30) NOT NULL,
  status_cd Char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (workflow_id)
)
;

-- Table rd_step

CREATE TABLE rd_step
(
  step_id Int NOT NULL AUTO_INCREMENT,
  descr Varchar(50) NOT NULL,
  status_cd Char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (step_id)
)
;

-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE project_object ADD CONSTRAINT Relationship1 FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE project_object ADD CONSTRAINT Relationship2 FOREIGN KEY (objectid) REFERENCES rd_object (objectid) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE project_archive ADD CONSTRAINT project_fk1 FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE project_archive_object ADD CONSTRAINT project_archive_fk1 FOREIGN KEY (archive_id) REFERENCES project_archive (archive_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE project_archive_object ADD CONSTRAINT rd_object_fk1 FOREIGN KEY (objectid) REFERENCES rd_object (objectid) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE project_archive ADD CONSTRAINT rd_environment_fk1 FOREIGN KEY (env_id) REFERENCES rd_environment (env_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE project ADD CONSTRAINT rd_user_fk1 FOREIGN KEY (owner_id) REFERENCES rd_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE project ADD CONSTRAINT rd_user_fk2 FOREIGN KEY (update_userid) REFERENCES rd_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE project ADD CONSTRAINT rd_workflow_fk1 FOREIGN KEY (workflow_id) REFERENCES rd_workflow (workflow_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE project ADD CONSTRAINT rd_step_fk1 FOREIGN KEY (step_id) REFERENCES rd_step (step_id) ON DELETE NO ACTION ON UPDATE NO ACTION
;

