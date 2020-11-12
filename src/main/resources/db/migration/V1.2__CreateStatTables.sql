

CREATE TABLE activity_cd
(
  sd_cd             VARCHAR(3)            NOT NULL,
  activity_cd       VARCHAR(10)           NOT NULL,
  descr             VARCHAR(35)           NOT NULL,
  comment_reqd      VARCHAR(1)            NOT NULL,
  bill_rate         DECIMAL(6,2)           NOT NULL,
  allow_descr_edit  VARCHAR(1)            NOT NULL,
  status_cd         VARCHAR(1)            NOT NULL,
  update_userid     VARCHAR(10)           NOT NULL
);

ALTER TABLE activity_cd ADD (
  CONSTRAINT activity_cd_pk1
  PRIMARY KEY
  (sd_cd, activity_cd));


Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'Big', 'This is a really big Activity Descr', 'N', 0, 
    'N', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'Big2', 'THIS IS A REALLY BIG ACTIVITY DESCW', 'N', 2.1, 
    'N', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'DEV', 'Create Archive Set/Mark for Migrati', 'Y', 0, 
    'N', 'A',  'KYANG');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'DEVDOC', 'Analyze and document required tasks', 'N', 2.5, 
    'N', 'A',  'KYANG');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'Devlog', 'Add Developer Logs (Check Complete)', 'N', 0, 
    'N', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'Doc-Post', 'Document Post-Changes', 'Y', 10, 
    'N', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'Doc-Pre', 'Document Pre-Change', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'MIGRREV', 'Document request for Prod migration', 'N', 0, 
    'N', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'Migration', 'Proceed to migrations', 'N', 0, 
    'Y', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'Notify-C', 'Notify Customer', 'Y', 3.25, 
    'Y', 'A',  'KYANG');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'Other', 'Other Activity-Specify', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'REUPPY', 'Refresh UPD From PRD - Payroll Day', 'N', 0, 
    'N', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'RUNCMPR', '1 - Run compare reports (DMO > PAT)', 'N', 0, 
    'N', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'Sch-Change', 'Schedule Change', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'Sch-Rlease', 'Schedule Release', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'TECHDOC', 'Technical Documentation Complete?', 'N', 0, 
    'N', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'ToSpecs', 'Tasks for Spec Transfer', 'N', 0, 
    'Y', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'Train', 'Training', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'UserSignOf', 'User Sign-off', 'N', 5.75, 
    'Y', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'APPRUAT', 'Document UAT test issues/concerns', 'N', 0, 
    'N', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'HOLD', 'Document reason for hold', 'Y', 0, 
    'Y', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'REJECT', 'Document reason for rejection', 'Y', 0, 
    'Y', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'CANCEL', 'Document reason for canceling', 'Y', 0, 
    'Y', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('ORA', 'CHG CNTL', 'Change Control Required', 'Y', 0, 
    'Y', 'A',  'HOLMSTROM');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('SQL', 'Doc-Post', 'Document Post-Changes', 'Y', 10, 
    'N', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('SQL', 'Doc-Pre', 'Document Pre-Change', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('SQL', 'Migration', 'Proceed to migrations', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('SQL', 'Notify-C', 'Notify Customer', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('SQL', 'Other', 'Other Activity-Specify', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('SQL', 'Sch-Change', 'Schedule Change', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('SQL', 'Sch-Rlease', 'Schedule Release', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('SQL', 'Train', 'Training', 'N', 0, 
    'Y', 'A',  'FVOLONTE');
Insert into activity_cd
   (sd_cd, activity_cd, descr, comment_reqd, bill_rate, 
    allow_descr_edit, status_cd,  update_userid)
 Values
   ('SQL', 'UserSignOf', 'User Sign-off', 'N', 0, 
    'Y', 'A',  'FVOLONTE');


CREATE TABLE service_domain
(
  sd_cd                VARCHAR(3)         NOT NULL,
  descr                VARCHAR(35)        NOT NULL,
  unit                 VARCHAR(20),
  address1             VARCHAR(50)        NOT NULL,
  address2             VARCHAR(50),
  address3             VARCHAR(50),
  phone_number         VARCHAR(13)        NOT NULL,
  city                 VARCHAR(20)        NOT NULL,
  state                VARCHAR(2)         NOT NULL,
  zip_code             VARCHAR(9)         NOT NULL,
  country              VARCHAR(3),
  csr_last_number      DECIMAL(10,0),
  csr_tab_ps           VARCHAR(1)         NOT NULL,
  csr_tab_applmgmt     VARCHAR(1)         NOT NULL,
  gappl_label          VARCHAR(20),
  csr_tab_task         VARCHAR(1)         NOT NULL,
  csr_tab_issue        VARCHAR(1)         DEFAULT  'N'   NOT NULL,
  csr_appl_env         VARCHAR(1)         NOT NULL,
  csr_chk_cust_count   VARCHAR(1)         NOT NULL,
  csr_close_lock_days  DECIMAL,
  use_daily_time       VARCHAR(1),
  use_ps_swat          VARCHAR(1)         DEFAULT  'Y',
  use_ps_locking       VARCHAR(1)         NOT NULL,
  use_file_swat        VARCHAR(1)         DEFAULT  'Y',
  cust_priority_req    VARCHAR(1)         DEFAULT  'N'    NOT NULL,
  status_cd            VARCHAR(1)         NOT NULL,
  update_userid        VARCHAR(10)        NOT NULL,
  csr_tab_oracle       VARCHAR(1)             DEFAULT 'N'    NOT NULL
);

ALTER TABLE service_domain ADD (
  CONSTRAINT service_domain_pk1
  PRIMARY KEY
  (sd_cd));

Insert into service_domain
   (sd_cd, descr, unit, address1, address2, 
    address3, phone_number, city, state, zip_code, 
    country, csr_last_number, csr_tab_ps, csr_tab_applmgmt, gappl_label, 
    csr_tab_task, csr_tab_issue, csr_appl_env, csr_chk_cust_count, csr_close_lock_days, 
    use_daily_time, use_ps_swat, use_ps_locking, use_file_swat, cust_priority_req, 
    status_cd, update_userid, csr_tab_oracle)
 Values
   ('ORA', 'Oracle', 'Oracle', '5 Polaris Way', 'Address (2)', 
    NULL, '9497548000', 'Aliso Viejo', 'CA', '92656', 
    NULL, 10126, 'Y', 'Y', 'Our Application', 
    'Y', 'Y', 'Y', 'N', NULL, 
    'Y', 'Y', 'Y', 'Y', 'N', 
    'A',  'VRAHIMI', 'Y');
Insert into service_domain
   (sd_cd, descr, unit, address1, address2, 
    address3, phone_number, city, state, zip_code, 
    country, csr_last_number, csr_tab_ps, csr_tab_applmgmt, gappl_label, 
    csr_tab_task, csr_tab_issue, csr_appl_env, csr_chk_cust_count, csr_close_lock_days, 
    use_daily_time, use_ps_swat, use_ps_locking, use_file_swat, cust_priority_req, 
    status_cd, update_userid, csr_tab_oracle)
 Values
   ('SQL', 'SQL Server FROM MICROSOFT CORPORATN', 'Quest', '5 Polaris Way', NULL, 
    NULL, '9497548000', 'Aliso Viejo', 'CA', '92656', 
    'USA', 30065, 'Y', 'Y', 'Our Application', 
    'Y', 'N', 'N', 'N', 30, 
    'Y', 'Y', 'Y', 'Y', 'N', 
    'A',  'KYANG', 'N');
	
	
CREATE TABLE db_config
(
  db_cd                    VARCHAR(10)    NOT NULL,
  descr                    VARCHAR(35)    NOT NULL,
  status_cd                VARCHAR(1)     NOT NULL,
  srv_logid                VARCHAR(50),
  srv_pwd                  VARCHAR(153),
  db_listener_port         INTEGER(15),
  hostname                 VARCHAR(255),
  service_name             VARCHAR(256),
  tns_entry                VARCHAR(1000),
  use_tns                  VARCHAR(1)     DEFAULT 'N'  NOT NULL,
  db_type				   VARCHAR(3)	  NOT NULL  
);

ALTER TABLE db_config ADD (
  CONSTRAINT db_config_pk1
  PRIMARY KEY
  (db_cd));
  
Insert into db_config
   (db_cd, descr, status_cd, srv_logid, srv_pwd, db_listener_port, hostname, service_name, tns_entry, use_tns, db_type)
Values
   ('d12g', 'd12g', 'A', 'apps', 'apps', 1521, 'alvrndorcl02.prod.quest.corp', 'd12g', 
    '(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=alvrndorcl02.prod.quest.corp)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=d12g)))', 'N', 'ORA');
	

CREATE TABLE server
(
  srv_cd                VARCHAR(10)       NOT NULL,
  descr                 VARCHAR(35)       NOT NULL,
  host                  VARCHAR(50)       NOT NULL,
  srv_home_dir          VARCHAR(255),
  srv_logid             VARCHAR(50)       NOT NULL,
  srv_pwd               VARCHAR(155),
  srv_port              VARCHAR(15)       NOT NULL,
  srv_cmd               VARCHAR(255),
  status_cd             VARCHAR(1)        NOT NULL,
  os                    VARCHAR(1)        DEFAULT 'U'   NOT NULL,
  auth_type             VARCHAR(1)        DEFAULT 'P'   NOT NULL,
  private_key           VARCHAR(5000)
);

ALTER TABLE server ADD (
  CONSTRAINT server_pk1
  PRIMARY KEY
  (srv_cd));
  
Insert into server
   (srv_cd, descr, host, srv_home_dir, srv_logid, 
    srv_pwd, srv_port, srv_cmd, status_cd, os, auth_type, private_key)
 Values
   ('unixd', 'Unix server - dev', '10.1.83.252', '', 'applmgr', 'applmgr' 
    , '22', null, 'A', 'U', 'P', '');

Insert into server
   (srv_cd, descr, host, srv_home_dir, srv_logid, 
    srv_pwd, srv_port, srv_cmd, status_cd, os, auth_type, private_key)
 Values
   ('windowsp', 'Windows server - prod', 'AZSD-MailFTPSvr.dev.quest.corp', 'E:/Statftp/PS_PROD', 'statftp', 'ftpStat!' 
    , '22', null, 'A', 'W', 'P', '');
