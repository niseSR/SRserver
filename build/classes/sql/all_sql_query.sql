<Create SQL 쿼리 문>


CREATE TABLE `tbl_user` (
	`USER_NUMSEQ` INT(50) NOT NULL AUTO_INCREMENT,
	`USER_ID` VARCHAR(200) NOT NULL,
	`USER_PASSWORD` VARCHAR(200) NOT NULL,
	`USER_NAME` VARCHAR(50) NOT NULL,
	`USER_COMPANY` VARCHAR(10) NOT NULL,
	`USER_REGDATE` DATE NOT NULL,
	`USER_SHCOUNT` INT(10) NULL DEFAULT '0',
	`USER_ASCOUNT` INT(10) NULL DEFAULT '0',
	`USER_THREATCOUNT` INT(10) NULL DEFAULT '0',
	PRIMARY KEY (`USER_NUMSEQ`)
)

CREATE TABLE `tbl_po_srrec` (
	`SRREC_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`SRREC_ID` VARCHAR(50) NOT NULL,
	`SRREC_DESCRIPTION` TEXT NULL,
	`SRREC_RELATEDSR` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`SRREC_NUMSEQ`)
)

CREATE TABLE `tbl_po_sr` (
	`SR_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`SR_ID` VARCHAR(50) NOT NULL,
	`SR_NAME` VARCHAR(50) NOT NULL,
	`SR_DESCRIPTION` TEXT NULL,
	PRIMARY KEY (`SR_NUMSEQ`)
)

CREATE TABLE `tbl_po_riskcl` (
	`RISKCL_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`RISKCL_ID` VARCHAR(50) NOT NULL,
	`RISKCL_NAME` VARCHAR(50) NOT NULL,
	`RISKCL_DESCRIPTION` TEXT NULL,
	PRIMARY KEY (`RISKCL_NUMSEQ`)
)

CREATE TABLE `tbl_po_relatedsr` (
	`RELATEDSR_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`RELATEDSR_SRID` VARCHAR(50) NOT NULL,
	`RELATEDSR_RELATIONSHIP` VARCHAR(50) NULL DEFAULT NULL,
	`RELATEDSR_RELATEDSRID` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`RELATEDSR_NUMSEQ`)
)

CREATE TABLE `tbl_po_implcm` (
	`IMPLCM_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`IMPLCM_ID` VARCHAR(50) NOT NULL,
	`IMPLCM_NAME` VARCHAR(50) NOT NULL,
	`IMPLCM_DESCRIPTION` TEXT NULL,
	`IMPLCM_SECSERVICE` TEXT NULL,
	PRIMARY KEY (`IMPLCM_NUMSEQ`)
)

CREATE TABLE `tbl_po_cwe` (
	`CWE_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`CWE_ID` VARCHAR(50) NOT NULL,
	`CWE_NAME` VARCHAR(50) NOT NULL,
	`CWE_DESCRIPTION` TEXT NULL,
	`CWE_RELATEDCAPEC` TEXT NULL,
	`CWE_RELATEDCWE` TEXT NULL,
	PRIMARY KEY (`CWE_NUMSEQ`)
)

CREATE TABLE `tbl_po_cve` (
	`CVE_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`CVE_ID` VARCHAR(50) NOT NULL,
	`CVE_NAME` VARCHAR(50) NOT NULL,
	`CVE_DESCRIPTION` TEXT NULL,
	PRIMARY KEY (`CVE_NUMSEQ`)
)

CREATE TABLE `tbl_po_cmsgoal` (
	`CMSGOAL_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`CMSGOAL_CMID` VARCHAR(50) NULL DEFAULT NULL,
	`CMSGOAL_CMNAME` VARCHAR(200) NULL DEFAULT NULL,
	`CMSGOAL_CMSGOAL` VARCHAR(50) NULL DEFAULT NULL,
	`CMSGOAL_ASID` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`CMSGOAL_NUMSEQ`)
)

CREATE TABLE `tbl_po_cm` (
	`CM_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`CM_ID` VARCHAR(50) NOT NULL,
	`CM_NAME` VARCHAR(200) NOT NULL,
	`CM_DESCRIPTION` TEXT NULL,
	PRIMARY KEY (`CM_NUMSEQ`)
)

CREATE TABLE `tbl_po_clrelatedcm` (
	`CLRELATEDCM_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`CLRELATEDCM_RISKCLID` VARCHAR(50) NULL DEFAULT NULL,
	`CLRELATEDCM_MITIGATEDCMTYPE` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`CLRELATEDCM_NUMSEQ`)
)

CREATE TABLE `tbl_po_capec` (
	`CAPEC_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`CAPEC_ID` VARCHAR(50) NOT NULL,
	`CAPEC_NAME` VARCHAR(50) NOT NULL,
	`CAPEC_DESCRIPTION` TEXT NULL,
	`CAPEC_PREREQUISITES` TEXT NULL,
	PRIMARY KEY (`CAPEC_NUMSEQ`)
)

CREATE TABLE `tbl_po_as` (
	`AS_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`AS_ID` VARCHAR(50) NOT NULL,
	`AS_NAME` VARCHAR(50) NOT NULL,
	`AS_DESCRIPTION` TEXT NULL,
	PRIMARY KEY (`AS_NUMSEQ`)
)

CREATE TABLE `tbl_do_threatpl` (
	`THREATPL_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`THREATPL_THREATID` VARCHAR(50) NOT NULL,
	`THREATPL_PLATFORMID` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`THREATPL_NUMSEQ`)
)

CREATE TABLE `tbl_do_threatact` (
	`THREATACT_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`THREATACT_THREATID` VARCHAR(50) NOT NULL,
	`THREATACT_ASID` VARCHAR(50) NOT NULL,
	`THREATACT_MGOAL` VARCHAR(10) NOT NULL,
	`THREATACT_ACTDESCRIPTION` TEXT NULL,
	`THREATACT_CAPECID` VARCHAR(50) NULL DEFAULT NULL,
	`THREATACT_CVEID` VARCHAR(50) NULL DEFAULT NULL,
	`THREATACT_CWEID` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`THREATACT_NUMSEQ`)
)

CREATE TABLE `tbl_do_threat` (
	`THREAT_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`THREAT_ID` VARCHAR(50) NOT NULL,
	`THREAT_NAME` VARCHAR(50) NOT NULL,
	`THREAT_DESCRIPTION` TEXT NULL,
	PRIMARY KEY (`THREAT_NUMSEQ`)
)

CREATE TABLE `tbl_do_relatedsh` (
	`RELATEDSH_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`RELATEDSH_ID` VARCHAR(50) NOT NULL,
	`RELATEDSH_NAME` VARCHAR(50) NOT NULL,
	`RELATEDSH_USERID` VARCHAR(250) NULL DEFAULT NULL,
	`RELATEDSH_DESCRIPTION` TEXT NULL,
	`RELATEDSH_COMPANY` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`RELATEDSH_NUMSEQ`)
)

CREATE TABLE `tbl_do_platform` (
	`PLATFORM_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`PLATFORM_ID` VARCHAR(200) NOT NULL,
	`PLATFORM_PART` VARCHAR(50) NOT NULL,
	`PLATFORM_VENDOR` VARCHAR(50) NOT NULL,
	`PLATFORM_PRODUCT` VARCHAR(100) NOT NULL,
	`PLATFORM_VERSION` VARCHAR(50) NULL DEFAULT NULL,
	`PLATFORM_UPDATE` VARCHAR(50) NULL DEFAULT NULL,
	`PLATFORM_EDITION` VARCHAR(50) NULL DEFAULT NULL,
	`PLATFORM_LANGUAGE` VARCHAR(50) NULL DEFAULT NULL,
	`PLATFORM_SOFTWAREEDITION` VARCHAR(50) NULL DEFAULT NULL,
	`PLATFORM_TARGETSOFTWARE` VARCHAR(50) NULL DEFAULT NULL,
	`PLATFORM_TARGETHARDWARE` VARCHAR(50) NULL DEFAULT NULL,
	`PLATFORM_OTHER` TEXT NULL,
	PRIMARY KEY (`PLATFORM_NUMSEQ`)
)

CREATE TABLE `tbl_do_domainasrelatedsh` (
	`DOMAINASRELATEDSH_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`DOMAINASRELATEDSH_DOMAINASID` VARCHAR(100) NOT NULL,
	`DOMAINASRELATEDSH_RELATEDSHID` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`DOMAINASRELATEDSH_NUMSEQ`)
)

CREATE TABLE `tbl_do_domainaspl` (
	`DOMAINASPL_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`DOMAINASPL_DOMAINASID` VARCHAR(100) NOT NULL,
	`DOMAINASPL_PLATFORMID` VARCHAR(200) NOT NULL,
	PRIMARY KEY (`DOMAINASPL_NUMSEQ`)
)

CREATE TABLE `tbl_do_domainas` (
	`DOMAINAS_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`DOMAINAS_ID` VARCHAR(100) NOT NULL,
	`DOMAINAS_USERID` VARCHAR(200) NOT NULL,
	`DOMAINAS_NAME` VARCHAR(200) NOT NULL,
	`DOMAINAS_DESCRIPTION` TEXT NULL,
	`DOMAINAS_SGOALC` VARCHAR(50) NOT NULL,
	`DOMAINAS_SGOALI` VARCHAR(50) NOT NULL,
	`DOMAINAS_SGOALA` VARCHAR(50) NOT NULL,
	`DOMAINAS_CRITICALITY` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`DOMAINAS_NUMSEQ`)
)

CREATE TABLE `tbl_do_currentcm` (
	`CURRENTCM_NUMSEQ` INT(10) NOT NULL AUTO_INCREMENT,
	`CURRENTCM_ID` VARCHAR(100) NOT NULL,
	`CURRENTCM_CMID` VARCHAR(50) NULL DEFAULT NULL,
	`CURRENTCM_USERID` VARCHAR(200) NULL DEFAULT NULL,
	`CURRENTCM_IMPLCMID` VARCHAR(50) NULL DEFAULT NULL,
	`CURRENTCM_IMPLCMNAME` VARCHAR(200) NULL DEFAULT NULL,
	`CURRENTCM_DOMAINASID` VARCHAR(100) NULL DEFAULT NULL,
	`CURRENTCM_DOMAINASNAME` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`CURRENTCM_NUMSEQ`)
)



===========================데이터 입력===============================


INSERT INTO `tbl_do_currentcm` (`CURRENTCM_NUMSEQ`, `CURRENTCM_ID`, `CURRENTCM_CMID`, `CURRENTCM_USERID`, `CURRENTCM_IMPLCMID`, `CURRENTCM_IMPLCMNAME`) VALUES (1, 'CM_DA_1_MA_1_1_CompanyBK', 'CM_DA_1_MA_1', 'fce217d55fb387ac629a3cae356c5d374a0bd6fc939c3100bafa67a7fc8531b8f52a19997b992f5a7833432331b084c6507039e6053339fc703276e6b098c991', 'CM_DA_1_MA_1_1', 'Peer Log Review Day');
INSERT INTO `tbl_do_currentcm` (`CURRENTCM_NUMSEQ`, `CURRENTCM_ID`, `CURRENTCM_CMID`, `CURRENTCM_USERID`, `CURRENTCM_IMPLCMID`, `CURRENTCM_IMPLCMNAME`) VALUES (2, 'CM_DA_1_RA_1_1_CompanyBK', 'CM_DA_1_RA_1', 'fce217d55fb387ac629a3cae356c5d374a0bd6fc939c3100bafa67a7fc8531b8f52a19997b992f5a7833432331b084c6507039e6053339fc703276e6b098c991', 'CM_DA_1_RA_1_1', 'OS Log Backup Program');

INSERT INTO `tbl_do_domainas` (`DOMAINAS_NUMSEQ`, `DOMAINAS_ID`, `DOMAINAS_USERID`, `DOMAINAS_NAME`, `DOMAINAS_DESCRIPTION`, `DOMAINAS_SGOALC`, `DOMAINAS_SGOALI`, `DOMAINAS_SGOALA`, `DOMAINAS_CRITICALITY`) VALUES (17, 'AS_DA_1_BKCompany_1', '7ab52a522fe8cd58e1c890330bdf4aedd4ce9508b0cb8fc369c3ca1beedbd3c51c61d388b0cc80e2ce09da5eb345aeffb06f912cfa75f970cec9c8b8e437cf3a', 'E-mail Server Log Data', 'E-mail Server Log data', 'C', 'RPRR', 'RM', 'H');
INSERT INTO `tbl_do_domainas` (`DOMAINAS_NUMSEQ`, `DOMAINAS_ID`, `DOMAINAS_USERID`, `DOMAINAS_NAME`, `DOMAINAS_DESCRIPTION`, `DOMAINAS_SGOALC`, `DOMAINAS_SGOALI`, `DOMAINAS_SGOALA`, `DOMAINAS_CRITICALITY`) VALUES (18, 'AS_DA_1_CompanyBK_1', 'fce217d55fb387ac629a3cae356c5d374a0bd6fc939c3100bafa67a7fc8531b8f52a19997b992f5a7833432331b084c6507039e6053339fc703276e6b098c991', 'E-mail Server Log Data', 'E-mail Server Log Data has the information about the log for access and exchange E-mail contents.', 'RP', 'C', 'RMRR', 'H');

INSERT INTO `tbl_do_domainaspl` (`DOMAINASPL_NUMSEQ`, `DOMAINASPL_DOMAINASID`, `DOMAINASPL_PLATFORMID`) VALUES (3, 'AS_DA_1_BKCompany_1', 'cpe:2.3:o:microsoft:windows_7:*:sp1:x86:*:*:*:*:*');
INSERT INTO `tbl_do_domainaspl` (`DOMAINASPL_NUMSEQ`, `DOMAINASPL_DOMAINASID`, `DOMAINASPL_PLATFORMID`) VALUES (4, 'AS_DA_1_BKCompany_1', 'cpe:2.3:o:microsoft:windows_7:*:sp1:x64:*:*:*:*:*');
INSERT INTO `tbl_do_domainaspl` (`DOMAINASPL_NUMSEQ`, `DOMAINASPL_DOMAINASID`, `DOMAINASPL_PLATFORMID`) VALUES (5, 'AS_DA_1_CompanyBK_1', 'cpe:2.3:o:microsoft:windows_7:*:sp1:x86:*:*:*:*:*');
INSERT INTO `tbl_do_domainaspl` (`DOMAINASPL_NUMSEQ`, `DOMAINASPL_DOMAINASID`, `DOMAINASPL_PLATFORMID`) VALUES (6, 'AS_DA_1_CompanyBK_1', 'cpe:2.3:o:microsoft:windows_7:*:*:x86:*:*:*:*:*');

INSERT INTO `tbl_do_domainasrelatedsh` (`DOMAINASRELATEDSH_NUMSEQ`, `DOMAINASRELATEDSH_DOMAINASID`, `DOMAINASRELATEDSH_RELATEDSHID`) VALUES (3, 'AS_DA_1_BKCompany_1', 'SH_BKCompany_1');
INSERT INTO `tbl_do_domainasrelatedsh` (`DOMAINASRELATEDSH_NUMSEQ`, `DOMAINASRELATEDSH_DOMAINASID`, `DOMAINASRELATEDSH_RELATEDSHID`) VALUES (4, 'AS_DA_1_BKCompany_1', 'SH_BKCompany_4');
INSERT INTO `tbl_do_domainasrelatedsh` (`DOMAINASRELATEDSH_NUMSEQ`, `DOMAINASRELATEDSH_DOMAINASID`, `DOMAINASRELATEDSH_RELATEDSHID`) VALUES (5, 'AS_DA_1_CompanyBK_1', 'SH_CompanyBK_1');
INSERT INTO `tbl_do_domainasrelatedsh` (`DOMAINASRELATEDSH_NUMSEQ`, `DOMAINASRELATEDSH_DOMAINASID`, `DOMAINASRELATEDSH_RELATEDSHID`) VALUES (6, 'AS_DA_1_CompanyBK_1', 'SH_CompanyBK_2');
INSERT INTO `tbl_do_domainasrelatedsh` (`DOMAINASRELATEDSH_NUMSEQ`, `DOMAINASRELATEDSH_DOMAINASID`, `DOMAINASRELATEDSH_RELATEDSHID`) VALUES (7, 'AS_DA_1_CompanyBK_1', 'SH_CompanyBK_3');
INSERT INTO `tbl_do_domainasrelatedsh` (`DOMAINASRELATEDSH_NUMSEQ`, `DOMAINASRELATEDSH_DOMAINASID`, `DOMAINASRELATEDSH_RELATEDSHID`) VALUES (8, 'AS_DA_1_CompanyBK_1', 'SH_CompanyBK_4');


INSERT INTO `tbl_do_platform` (`PLATFORM_NUMSEQ`, `PLATFORM_ID`, `PLATFORM_PART`, `PLATFORM_VENDOR`, `PLATFORM_PRODUCT`, `PLATFORM_VERSION`, `PLATFORM_UPDATE`, `PLATFORM_EDITION`, `PLATFORM_LANGUAGE`, `PLATFORM_SOFTWAREEDITION`, `PLATFORM_TARGETSOFTWARE`, `PLATFORM_TARGETHARDWARE`, `PLATFORM_OTHER`) VALUES (10, 'cpe:2.3:o:microsoft:windows_7:*:sp1:x86:*:*:*:*:*', 'o', 'microsoft', 'windows_7', '*', 'sp1', 'x86', '*', '*', '*', '*', '*');
INSERT INTO `tbl_do_platform` (`PLATFORM_NUMSEQ`, `PLATFORM_ID`, `PLATFORM_PART`, `PLATFORM_VENDOR`, `PLATFORM_PRODUCT`, `PLATFORM_VERSION`, `PLATFORM_UPDATE`, `PLATFORM_EDITION`, `PLATFORM_LANGUAGE`, `PLATFORM_SOFTWAREEDITION`, `PLATFORM_TARGETSOFTWARE`, `PLATFORM_TARGETHARDWARE`, `PLATFORM_OTHER`) VALUES (11, 'cpe:2.3:o:microsoft:windows_7:*:sp1:x64:*:*:*:*:*', 'o', 'microsoft', 'windows_7', '*', 'sp1', 'x64', '*', '*', '*', '*', '*');
INSERT INTO `tbl_do_platform` (`PLATFORM_NUMSEQ`, `PLATFORM_ID`, `PLATFORM_PART`, `PLATFORM_VENDOR`, `PLATFORM_PRODUCT`, `PLATFORM_VERSION`, `PLATFORM_UPDATE`, `PLATFORM_EDITION`, `PLATFORM_LANGUAGE`, `PLATFORM_SOFTWAREEDITION`, `PLATFORM_TARGETSOFTWARE`, `PLATFORM_TARGETHARDWARE`, `PLATFORM_OTHER`) VALUES (12, 'cpe:2.3:o:microsoft:windows_7:*:*:x86:*:*:*:*:*', 'o', 'microsoft', 'windows_7', '*', '*', 'x86', '*', '*', '*', '*', '*');
INSERT INTO `tbl_do_platform` (`PLATFORM_NUMSEQ`, `PLATFORM_ID`, `PLATFORM_PART`, `PLATFORM_VENDOR`, `PLATFORM_PRODUCT`, `PLATFORM_VERSION`, `PLATFORM_UPDATE`, `PLATFORM_EDITION`, `PLATFORM_LANGUAGE`, `PLATFORM_SOFTWAREEDITION`, `PLATFORM_TARGETSOFTWARE`, `PLATFORM_TARGETHARDWARE`, `PLATFORM_OTHER`) VALUES (13, 'cpe:2.3:o:microsoft:windows_7:*:*:x64:*:*:*:*:*', 'o', 'microsoft', 'windows_7', '*', '*', 'x64', '*', '*', '*', '*', '*');

INSERT INTO `tbl_do_relatedsh` (`RELATEDSH_NUMSEQ`, `RELATEDSH_ID`, `RELATEDSH_NAME`, `RELATEDSH_USERID`, `RELATEDSH_DESCRIPTION`, `RELATEDSH_COMPANY`) VALUES (1, 'SH_BKCompany_1', 'DEV Team', '7ab52a522fe8cd58e1c890330bdf4aedd4ce9508b0cb8fc369c3ca1beedbd3c51c61d388b0cc80e2ce09da5eb345aeffb06f912cfa75f970cec9c8b8e437cf3a', 'DEV Team is the development team which creates the profit for company developing mobile software.', 'BKCompany');
INSERT INTO `tbl_do_relatedsh` (`RELATEDSH_NUMSEQ`, `RELATEDSH_ID`, `RELATEDSH_NAME`, `RELATEDSH_USERID`, `RELATEDSH_DESCRIPTION`, `RELATEDSH_COMPANY`) VALUES (2, 'SH_BKCompany_2', 'General Affair', '7ab52a522fe8cd58e1c890330bdf4aedd4ce9508b0cb8fc369c3ca1beedbd3c51c61d388b0cc80e2ce09da5eb345aeffb06f912cfa75f970cec9c8b8e437cf3a', 'General Affair is the team which operates company coping with maintenance or supports for the DEV team.', 'BKCompany');
INSERT INTO `tbl_do_relatedsh` (`RELATEDSH_NUMSEQ`, `RELATEDSH_ID`, `RELATEDSH_NAME`, `RELATEDSH_USERID`, `RELATEDSH_DESCRIPTION`, `RELATEDSH_COMPANY`) VALUES (3, 'SH_BKCompany_3', 'Account Team', '7ab52a522fe8cd58e1c890330bdf4aedd4ce9508b0cb8fc369c3ca1beedbd3c51c61d388b0cc80e2ce09da5eb345aeffb06f912cfa75f970cec9c8b8e437cf3a', 'Account Team supports the whole company coping with money, staff\'s account information, and other profit related work.', 'BKCompany');
INSERT INTO `tbl_do_relatedsh` (`RELATEDSH_NUMSEQ`, `RELATEDSH_ID`, `RELATEDSH_NAME`, `RELATEDSH_USERID`, `RELATEDSH_DESCRIPTION`, `RELATEDSH_COMPANY`) VALUES (4, 'SH_BKCompany_4', 'System Management', '7ab52a522fe8cd58e1c890330bdf4aedd4ce9508b0cb8fc369c3ca1beedbd3c51c61d388b0cc80e2ce09da5eb345aeffb06f912cfa75f970cec9c8b8e437cf3a', 'System Management is the team which support technically with various system to whole company.', 'BKCompany');
INSERT INTO `tbl_do_relatedsh` (`RELATEDSH_NUMSEQ`, `RELATEDSH_ID`, `RELATEDSH_NAME`, `RELATEDSH_USERID`, `RELATEDSH_DESCRIPTION`, `RELATEDSH_COMPANY`) VALUES (5, 'SH_CompanyBK_1', 'DEV Team', 'fce217d55fb387ac629a3cae356c5d374a0bd6fc939c3100bafa67a7fc8531b8f52a19997b992f5a7833432331b084c6507039e6053339fc703276e6b098c991', 'Dev Team is the development team which creates the profit for company developing mobile software.', 'CompanyBK');
INSERT INTO `tbl_do_relatedsh` (`RELATEDSH_NUMSEQ`, `RELATEDSH_ID`, `RELATEDSH_NAME`, `RELATEDSH_USERID`, `RELATEDSH_DESCRIPTION`, `RELATEDSH_COMPANY`) VALUES (6, 'SH_CompanyBK_2', 'General Affair', 'fce217d55fb387ac629a3cae356c5d374a0bd6fc939c3100bafa67a7fc8531b8f52a19997b992f5a7833432331b084c6507039e6053339fc703276e6b098c991', 'General Affair is the team which operates company supports for the DEV team.', 'CompanyBK');
INSERT INTO `tbl_do_relatedsh` (`RELATEDSH_NUMSEQ`, `RELATEDSH_ID`, `RELATEDSH_NAME`, `RELATEDSH_USERID`, `RELATEDSH_DESCRIPTION`, `RELATEDSH_COMPANY`) VALUES (7, 'SH_CompanyBK_3', 'Account Team', 'fce217d55fb387ac629a3cae356c5d374a0bd6fc939c3100bafa67a7fc8531b8f52a19997b992f5a7833432331b084c6507039e6053339fc703276e6b098c991', 'Account team is the whole company coping with staff\'s account information and other financial things', 'CompanyBK');
INSERT INTO `tbl_do_relatedsh` (`RELATEDSH_NUMSEQ`, `RELATEDSH_ID`, `RELATEDSH_NAME`, `RELATEDSH_USERID`, `RELATEDSH_DESCRIPTION`, `RELATEDSH_COMPANY`) VALUES (8, 'SH_CompanyBK_4', 'System Management', 'fce217d55fb387ac629a3cae356c5d374a0bd6fc939c3100bafa67a7fc8531b8f52a19997b992f5a7833432331b084c6507039e6053339fc703276e6b098c991', 'System management is the team which support technically with various system to whole company.', 'CompanyBK');

INSERT INTO `tbl_do_threat` (`THREAT_NUMSEQ`, `THREAT_ID`, `THREAT_NAME`, `THREAT_DESCRIPTION`) VALUES (15, 'TH_BKCompany_1', 'Hacktool.Eventlog', '해킹툴의 일종으로서 Log Data를 조작하게 하는 역할을 하는 다기능 툴임');
INSERT INTO `tbl_do_threat` (`THREAT_NUMSEQ`, `THREAT_ID`, `THREAT_NAME`, `THREAT_DESCRIPTION`) VALUES (16, 'TH_BKCompany_2', 'hacktool.eventlog', 'asnv;lwnv;oinasd;lvknas;lkvnaw;oiebo;iaweg');
INSERT INTO `tbl_do_threat` (`THREAT_NUMSEQ`, `THREAT_ID`, `THREAT_NAME`, `THREAT_DESCRIPTION`) VALUES (17, 'TH_BKCompany_3', 'eventlog', 'This is eventlog threat against server log file');
INSERT INTO `tbl_do_threat` (`THREAT_NUMSEQ`, `THREAT_ID`, `THREAT_NAME`, `THREAT_DESCRIPTION`) VALUES (18, 'TH_CompanyBK_1', 'Hacktool.eventlog', ' hacking tool which used by Butterfly for parsing, analyzing, and delete Log data. It also has self-delete function.');

INSERT INTO `tbl_do_threatact` (`THREATACT_NUMSEQ`, `THREATACT_THREATID`, `THREATACT_ASID`, `THREATACT_MGOAL`, `THREATACT_ACTDESCRIPTION`, `THREATACT_CAPECID`, `THREATACT_CVEID`, `THREATACT_CWEID`) VALUES (4, 'TH_BKCompany_1', 'AS_DA_1', 'M,D', '* Deletes Selected Entry\n* Securely Deleted itself\n* Delete Files', 'CAPEC_268', '', 'CWE_440');
INSERT INTO `tbl_do_threatact` (`THREATACT_NUMSEQ`, `THREATACT_THREATID`, `THREATACT_ASID`, `THREATACT_MGOAL`, `THREATACT_ACTDESCRIPTION`, `THREATACT_CAPECID`, `THREATACT_CVEID`, `THREATACT_CWEID`) VALUES (5, 'TH_BKCompany_1', 'AS_DA_1', 'E', '* Flush all logs to disk\r\n* Gather and save log on and log off events from the security channel\r\n* Gather and save all logs from a channel', 'CAPEC_157', NULL, 'CWE_311');
INSERT INTO `tbl_do_threatact` (`THREATACT_NUMSEQ`, `THREATACT_THREATID`, `THREATACT_ASID`, `THREATACT_MGOAL`, `THREATACT_ACTDESCRIPTION`, `THREATACT_CAPECID`, `THREATACT_CVEID`, `THREATACT_CWEID`) VALUES (6, 'TH_BKCompany_2', 'AS_DA_1', 'E,M', 'aeawvasdvawsev', 'CAPEC_268', NULL, 'CWE_440');
INSERT INTO `tbl_do_threatact` (`THREATACT_NUMSEQ`, `THREATACT_THREATID`, `THREATACT_ASID`, `THREATACT_MGOAL`, `THREATACT_ACTDESCRIPTION`, `THREATACT_CAPECID`, `THREATACT_CVEID`, `THREATACT_CWEID`) VALUES (7, 'TH_CompanyBK_1', 'AS_DA_1', 'M,D', '* Delete Selected Entry\n* Securely Deleted itself\n* Delete Files', 'CAPEC_268', '', 'CWE_440');
INSERT INTO `tbl_do_threatact` (`THREATACT_NUMSEQ`, `THREATACT_THREATID`, `THREATACT_ASID`, `THREATACT_MGOAL`, `THREATACT_ACTDESCRIPTION`, `THREATACT_CAPECID`, `THREATACT_CVEID`, `THREATACT_CWEID`) VALUES (8, 'TH_CompanyBK_1', 'AS_DA_1', 'E', '* Flush all logs to disk\n* Gather and Save log on and log off events from the security channel', 'CAPEC_157', '', 'CWE_311');

INSERT INTO `tbl_do_threatpl` (`THREATPL_NUMSEQ`, `THREATPL_THREATID`, `THREATPL_PLATFORMID`) VALUES (10, 'TH_BKCompany_1', 'cpe:2.3:o:microsoft:windows_7:*:sp1:x86:*:*:*:*:*');
INSERT INTO `tbl_do_threatpl` (`THREATPL_NUMSEQ`, `THREATPL_THREATID`, `THREATPL_PLATFORMID`) VALUES (11, 'TH_BKCompany_1', 'cpe:2.3:o:microsoft:windows_7:*:sp1:x64:*:*:*:*:*');
INSERT INTO `tbl_do_threatpl` (`THREATPL_NUMSEQ`, `THREATPL_THREATID`, `THREATPL_PLATFORMID`) VALUES (12, 'TH_BKCompany_2', 'cpe:2.3:o:microsoft:windows_7:*:sp1:x86:*:*:*:*:*');
INSERT INTO `tbl_do_threatpl` (`THREATPL_NUMSEQ`, `THREATPL_THREATID`, `THREATPL_PLATFORMID`) VALUES (13, 'TH_BKCompany_3', 'cpe:2.3:o:microsoft:windows_7:*:sp1:x86:*:*:*:*:*');
INSERT INTO `tbl_do_threatpl` (`THREATPL_NUMSEQ`, `THREATPL_THREATID`, `THREATPL_PLATFORMID`) VALUES (14, 'TH_CompanyBK_1', 'cpe:2.3:o:microsoft:windows_7:*:sp1:x86:*:*:*:*:*');
INSERT INTO `tbl_do_threatpl` (`THREATPL_NUMSEQ`, `THREATPL_THREATID`, `THREATPL_PLATFORMID`) VALUES (15, 'TH_CompanyBK_1', 'cpe:2.3:o:microsoft:windows_7:*:*:x86:*:*:*:*:*');

INSERT INTO `tbl_po_as` (`AS_NUMSEQ`, `AS_ID`, `AS_NAME`, `AS_DESCRIPTION`) VALUES (1, 'AS_DA_1', 'Log Data', 'This is information which is written in computer system for Audit for the future. It contains system activity, user access, etc ');
INSERT INTO `tbl_po_as` (`AS_NUMSEQ`, `AS_ID`, `AS_NAME`, `AS_DESCRIPTION`) VALUES (2, 'AS_DA_2', 'Personnel Information', 'This is information about the custommer name, phone number, and birthday.');
INSERT INTO `tbl_po_as` (`AS_NUMSEQ`, `AS_ID`, `AS_NAME`, `AS_DESCRIPTION`) VALUES (3, 'AS_DA_3', 'Company Profit Data', 'This is information about the ');

INSERT INTO `tbl_po_capec` (`CAPEC_NUMSEQ`, `CAPEC_ID`, `CAPEC_NAME`, `CAPEC_DESCRIPTION`, `CAPEC_PREREQUISITES`) VALUES (1, 'CAPEC_268', 'Audit Log Manipulation', 'The attacker injects, manipulates, deletes, or forges malicious log entries into the log file, in an attempt to mislead an audit of the log file or cover tracks of an attack. Due to either insufficient access controls of the log files or the logging mechanism, the attacker is able to perform such actions.', '* The target host is logging the action and data of the user.\r\n* The target host insufficiently protects access to the logs or logging mechanisms.\r\n');
INSERT INTO `tbl_po_capec` (`CAPEC_NUMSEQ`, `CAPEC_ID`, `CAPEC_NAME`, `CAPEC_DESCRIPTION`, `CAPEC_PREREQUISITES`) VALUES (2, 'CAPEC_35', 'Leverage Executable Code in Non-Executable Files', 'An attack of this type exploits a system\'s trust in configuration and resource files, when the executable loads the resource (such as an image file or configuration file) the attacker has modified the file to either execute malicious code directly or manipulate the target process (e.g. application server) to execute based on the malicious configuration parameters. Since systems are increasingly interrelated mashing up resources from local and remote sources the possibility of this attack occurring is high.\r\nThe attack can be directed at a client system, such as causing buffer overrun through loading seemingly benign image files, as in Microsoft Security Bulletin MS04-028 where specially crafted JPEG files could cause a buffer overrun once loaded into the browser. Another example targets clients reading pdf files. In this case the attacker simply appends javascript to the end of a legitimate url for a pdf (http://www.gnucitizen.org/blog/danger-danger-danger/)\r\nhttp://path/to/pdf/file.pdf#whatever_name_you_want=javascript:your_code_here\r\nThe client assumes that they are reading a pdf, but the attacker has modified the resource and loaded executable javascript into the client\'s browser process.\r\nThe attack can also target server processes. The attacker edits the resource or configuration file, for example a web.xml file used to configure security permissions for a J2EE app server, adding role name "public" grants all users with the public role the ability to use the administration functionality.', '* The attacker must have the ability to modify non-executable files consumed by the target software.');
INSERT INTO `tbl_po_capec` (`CAPEC_NUMSEQ`, `CAPEC_ID`, `CAPEC_NAME`, `CAPEC_DESCRIPTION`, `CAPEC_PREREQUISITES`) VALUES (3, 'CAPEC_157', 'Sniffing Attacks', 'An attacker monitors information transmitted between logical or physical nodes of a network. The attacker need not be able to prevent reception or change content but must simply be able to observe and read the traffic. The attacker might precipitate or indirectly influence the content of the observed transaction, but the attacker is never the intended recipient of the information. Any transmission medium can theoretically be sniffed if the attacker can listen to the contents between the sender and recipient.', '* Any target that transmits readable data could be attacked in this way. Cryptographic techniques that render a data-stream unreadable can thwart this type of attack.');

INSERT INTO `tbl_po_clrelatedcm` (`CLRELATEDCM_NUMSEQ`, `CLRELATEDCM_RISKCLID`, `CLRELATEDCM_MITIGATEDCMTYPE`) VALUES (1, 'CL_DA_7', 'MI');
INSERT INTO `tbl_po_clrelatedcm` (`CLRELATEDCM_NUMSEQ`, `CLRELATEDCM_RISKCLID`, `CLRELATEDCM_MITIGATEDCMTYPE`) VALUES (2, 'CL_DA_7', 'MA');
INSERT INTO `tbl_po_clrelatedcm` (`CLRELATEDCM_NUMSEQ`, `CLRELATEDCM_RISKCLID`, `CLRELATEDCM_MITIGATEDCMTYPE`) VALUES (3, 'CL_DA_7', 'PI');
INSERT INTO `tbl_po_clrelatedcm` (`CLRELATEDCM_NUMSEQ`, `CLRELATEDCM_RISKCLID`, `CLRELATEDCM_MITIGATEDCMTYPE`) VALUES (4, 'CL_DA_7', 'PM');

INSERT INTO `tbl_po_cm` (`CM_NUMSEQ`, `CM_ID`, `CM_NAME`, `CM_DESCRIPTION`) VALUES (1, 'CM_DA_1_MI_1', 'File Integrity Monitoring', 'Internal control or process which performs the act of validating the integrity of operating system and application software files using a verification method between the current file state and the known, good baseline.');
INSERT INTO `tbl_po_cm` (`CM_NUMSEQ`, `CM_ID`, `CM_NAME`, `CM_DESCRIPTION`) VALUES (2, 'CM_DA_1_PI_1', 'File Integrity Monitoring', 'Internal control or process which performs the act of validating the integrity of operating system and application software files using a verification method between the current file state and the known, good baseline.');
INSERT INTO `tbl_po_cm` (`CM_NUMSEQ`, `CM_ID`, `CM_NAME`, `CM_DESCRIPTION`) VALUES (3, 'CM_DA_1_PI_2', 'Vaccine Agent', 'The agent perform to prevent any malicious action against the company asset.');
INSERT INTO `tbl_po_cm` (`CM_NUMSEQ`, `CM_ID`, `CM_NAME`, `CM_DESCRIPTION`) VALUES (4, 'CM_DA_1_RI_1', 'Synchronize Log Data', 'The Log data will be synchronized with other storage prepared in physically separated sites. ');
INSERT INTO `tbl_po_cm` (`CM_NUMSEQ`, `CM_ID`, `CM_NAME`, `CM_DESCRIPTION`) VALUES (5, 'CM_DA_1_MA_1', 'Log Review', 'The lgo data will be reviwed by other members who has IT experiences.');
INSERT INTO `tbl_po_cm` (`CM_NUMSEQ`, `CM_ID`, `CM_NAME`, `CM_DESCRIPTION`) VALUES (6, 'CM_DA_1_MC_1', 'Server Access Control', 'Server Access Control with Logging in the file system.');
INSERT INTO `tbl_po_cm` (`CM_NUMSEQ`, `CM_ID`, `CM_NAME`, `CM_DESCRIPTION`) VALUES (7, 'CM_DA_1_RA_1', 'Log Backup', 'Backup the Log file in order to recover the log data in case of incident');


INSERT INTO `tbl_po_cmsgoal` (`CMSGOAL_NUMSEQ`, `CMSGOAL_CMID`, `CMSGOAL_CMNAME`, `CMSGOAL_CMSGOAL`, `CMSGOAL_ASID`) VALUES (1, 'CM_DA_1_MI_1', 'File Integrity Monitoring', 'MI', 'AS_DA_1');
INSERT INTO `tbl_po_cmsgoal` (`CMSGOAL_NUMSEQ`, `CMSGOAL_CMID`, `CMSGOAL_CMNAME`, `CMSGOAL_CMSGOAL`, `CMSGOAL_ASID`) VALUES (2, 'CM_DA_1_PI_1', 'File Integrity Monitoring', 'PI', 'AS_DA_1');
INSERT INTO `tbl_po_cmsgoal` (`CMSGOAL_NUMSEQ`, `CMSGOAL_CMID`, `CMSGOAL_CMNAME`, `CMSGOAL_CMSGOAL`, `CMSGOAL_ASID`) VALUES (3, 'CM_DA_1_RI_1', 'Synchronize Log Data', 'RI', 'AS_DA_1');
INSERT INTO `tbl_po_cmsgoal` (`CMSGOAL_NUMSEQ`, `CMSGOAL_CMID`, `CMSGOAL_CMNAME`, `CMSGOAL_CMSGOAL`, `CMSGOAL_ASID`) VALUES (4, 'CM_DA_1_MA_1', 'Log Review', 'MA', 'AS_DA_1');
INSERT INTO `tbl_po_cmsgoal` (`CMSGOAL_NUMSEQ`, `CMSGOAL_CMID`, `CMSGOAL_CMNAME`, `CMSGOAL_CMSGOAL`, `CMSGOAL_ASID`) VALUES (5, 'CM_DA_1_RA_1', 'Log Backup', 'RA', 'AS_DA_1');

INSERT INTO `tbl_po_cwe` (`CWE_NUMSEQ`, `CWE_ID`, `CWE_NAME`, `CWE_DESCRIPTION`, `CWE_RELATEDCAPEC`, `CWE_RELATEDCWE`) VALUES (1, 'CWE_440', 'Expected Behavior Violation', 'A feature, API, or function being used by a product behaves differently than the product expects.', '* CAPEC_35\r\n* CAPEC_268', '* CWE_438 Behavioral Problem\r\n* CWE_684 Incorrect Provision of Specified Functionality');
INSERT INTO `tbl_po_cwe` (`CWE_NUMSEQ`, `CWE_ID`, `CWE_NAME`, `CWE_DESCRIPTION`, `CWE_RELATEDCAPEC`, `CWE_RELATEDCWE`) VALUES (2, 'CWE_275', 'Permission Issues', 'Weaknesses in this category are related to improper assignment or handling of permissions.', '* CAPEC_17 Accessing, Modifying or Executing Executable Files\r\n* CAPEC_35 Leveage Executable Code in Non-Executable Files', '* CWE_264 Permissions, Privileges, and Access Controls\r\n* CWE_632 Weakness that Affect Files or Directories\r\n* CWE_61 UNIX Symbolic Link(Symlink) Following');
INSERT INTO `tbl_po_cwe` (`CWE_NUMSEQ`, `CWE_ID`, `CWE_NAME`, `CWE_DESCRIPTION`, `CWE_RELATEDCAPEC`, `CWE_RELATEDCWE`) VALUES (3, 'CWE_311', 'Missing Encryption of Sensitive Data', 'The software doesnot encrypt sensitive or critical information before storage or transmission.', '* CPAEC_117 Interception \r\n* CPAEC_155 Screen Temporary Files for Sensitive Information\r\n* CPAEC_157 Sniffing Attacks\r\n* CPAEC_158 Sniffing Network Traffic\r\n* CPAEC_65 Sniff Application Code', '* CWE_310 Cryptographic Issues\r\n* CWE_693 Protection Mechanism Failure\r\n* CWE_312 Cleartext Storage of Sensitive Information\r\n* CWE_319 Cleartext Transmission of Sensitive Information\r\n* CWE_614 Sensitive Cookie in HTTPS Session Without \'Secure\' Attribute');

INSERT INTO `tbl_po_implcm` (`IMPLCM_NUMSEQ`, `IMPLCM_ID`, `IMPLCM_NAME`, `IMPLCM_DESCRIPTION`, `IMPLCM_SECSERVICE`) VALUES (1, 'CM_DA_1_PI_1_1', 'Advanced Intrusion Detection Enviornment', 'The Advanced Intrusion Detection Environment (AIDE) was initially developed as a free replacement for Tripwire licensed under the terms of the GNU General Public License (GPL).', '* Snapshot');
INSERT INTO `tbl_po_implcm` (`IMPLCM_NUMSEQ`, `IMPLCM_ID`, `IMPLCM_NAME`, `IMPLCM_DESCRIPTION`, `IMPLCM_SECSERVICE`) VALUES (2, 'CM_DA_1_PI_1_2', 'OSSEC', 'OSSEC is a free, open-source host-based intrusion detection system (HIDS). It performs log analysis, integrity checking, Windows registry monitoring, rootkit detection, time-based alerting, and active response. It provides intrusion detection for most operating systems', NULL);
INSERT INTO `tbl_po_implcm` (`IMPLCM_NUMSEQ`, `IMPLCM_ID`, `IMPLCM_NAME`, `IMPLCM_DESCRIPTION`, `IMPLCM_SECSERVICE`) VALUES (3, 'CM_DA_1_RA_1_1', 'OS Log Backup Program', 'The Log Backup program support backing up the log file into the other media', NULL);
INSERT INTO `tbl_po_implcm` (`IMPLCM_NUMSEQ`, `IMPLCM_ID`, `IMPLCM_NAME`, `IMPLCM_DESCRIPTION`, `IMPLCM_SECSERVICE`) VALUES (4, 'CM_DA_1_MA_1_1', 'Peer Log Review Day', 'The selected day to review log file mandatory', NULL);

INSERT INTO `tbl_po_relatedsr` (`RELATEDSR_NUMSEQ`, `RELATEDSR_SRID`, `RELATEDSR_RELATIONSHIP`, `RELATEDSR_RELATEDSRID`) VALUES (1, 'SR_11_6_2', 'UP', 'SR_11_6');
INSERT INTO `tbl_po_relatedsr` (`RELATEDSR_NUMSEQ`, `RELATEDSR_SRID`, `RELATEDSR_RELATIONSHIP`, `RELATEDSR_RELATEDSRID`) VALUES (2, 'SR_11_6_2', 'DOWN', 'SR_11_6_2_1');
INSERT INTO `tbl_po_relatedsr` (`RELATEDSR_NUMSEQ`, `RELATEDSR_SRID`, `RELATEDSR_RELATIONSHIP`, `RELATEDSR_RELATEDSRID`) VALUES (3, 'SR_11_6_2', 'DOWN', 'SR_11_6_2_2');

INSERT INTO `tbl_po_riskcl` (`RISKCL_NUMSEQ`, `RISKCL_ID`, `RISKCL_NAME`, `RISKCL_DESCRIPTION`) VALUES (1, 'CL_DA_7', 'Mistreat Data', 'Data can be mistreated or modified illegally due to not to define the criticality of data in policy, not to designate the criticality of each data, to implement insufficient control or to over-implement control.');

INSERT INTO `tbl_po_sr` (`SR_NUMSEQ`, `SR_ID`, `SR_NAME`, `SR_DESCRIPTION`) VALUES (1, 'SR_11_6_2', 'Logging and Backup', 'The type of logging, such as information system, application, security system, etc., must be defined, preserved, and reviewed periodically based on legal compliance');
INSERT INTO `tbl_po_sr` (`SR_NUMSEQ`, `SR_ID`, `SR_NAME`, `SR_DESCRIPTION`) VALUES (2, 'SR_11_6_2_1', 'Logging and Backup1', 'Logging procedure for the critical information system must be defined and activated .');
INSERT INTO `tbl_po_sr` (`SR_NUMSEQ`, `SR_ID`, `SR_NAME`, `SR_DESCRIPTION`) VALUES (3, 'SR_11_6_2_2', 'Logging and Backup2', 'The log file must be backed up using separated media and authorized least users for accessing the log file.');
INSERT INTO `tbl_po_sr` (`SR_NUMSEQ`, `SR_ID`, `SR_NAME`, `SR_DESCRIPTION`) VALUES (4, 'SR_11_6', 'Log Management and Monitoring', 'The Log must be managed and monitored');

INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (1, 'SR_11_6_2_2_B_OR_DA_1_PI_1_1', 'Designated One Person who has IT knowledge', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (2, 'SR_11_6_2_2_B_OR_DA_1_PI_1_2', 'Designated Organization which has IT knowledge', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (3, 'SR_11_6_2_2_B_OR_DA_1_PI_1_3', 'Outsourcing Company', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (4, 'SR_11_6_2_2_B_RP_DA_1_PI_1_1', 'Regulation Documents', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (5, 'SR_11_6_2_2_B_RP_DA_1_PI_1_2', 'Policy Documents', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (6, 'SR_11_6_2_2_B_RP_DA_1_PI_1_3', 'System Manual Documents', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (7, 'SR_11_6_2_2_B_RP_DA_1_PI_1_4', 'Outsourcing Contract Documents', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (8, 'SR_11_6_2_2_B_RF_DA_1_PI_1_1', 'Allocate $300 per month for outsourcing', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (9, 'SR_11_6_2_2_B_TI_DA_1_PI_1_1', 'Allocate 1 hours per week for supervising outsourcing company', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (10, 'SR_11_6_2_2_B_BP_DA_1_PI_1_1', 'Monitoring Procedures', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (11, 'SR_11_6_2_2_B_BP_DA_1_PI_1_2', 'Reporting Procedures', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (12, 'SR_11_6_2_2_B_BP_DA_1_PI_1_3', 'Service Recovery Procedures', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (13, 'SR_11_6_2_2_B_BP_DA_1_PI_1_4', 'Training Plan', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (14, 'SR_11_6_2_2_B_BP_DA_1_PI_1_5', 'Training Contracts', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (15, 'SR_11_6_2_2_B_LC_DA_1_PI_1_1', 'Act on Promotion of Information and Communication Network Utilization and Information Protection, etc. Article 15.', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (16, 'SR_11_6_2_2_S_SE_DA_1_PI_1_1', 'Log File Integrity Monitoring', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (17, 'SR_11_6_2_2_S_SE_DA_1_PI_1_2', 'Log File Access Control', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (18, 'SR_11_6_2_2_S_PL_DA_1_PI_1_1', 'Windows 7 Server', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (19, 'SR_11_6_2_2_S_PL_DA_1_PI_1_2', 'Windows Vista Server', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (20, 'SR_11_6_2_2_T_SM_DA_1_PI_1_1', 'File Checksum', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (21, 'SR_11_6_2_2_T_SM_DA_1_PI_1_2', 'File Role based Access Control', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (22, 'SR_11_6_2_2_T_SM_DA_1_PI_1_3', 'File Discrete Access Control', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (23, 'SR_11_6_2_2_T_TT_DA_1_PI_1_1', 'Within 1 month published technology', NULL);
INSERT INTO `tbl_po_srrec` (`SRREC_NUMSEQ`, `SRREC_ID`, `SRREC_DESCRIPTION`, `SRREC_RELATEDSR`) VALUES (24, 'SR_11_6_2_2_T_TT_DA_1_PI_1_2', 'Within 3 month published technology', NULL);

INSERT INTO `tbl_user` (`USER_NUMSEQ`, `USER_ID`, `USER_PASSWORD`, `USER_NAME`, `USER_COMPANY`, `USER_REGDATE`, `USER_SHCOUNT`, `USER_ASCOUNT`, `USER_THREATCOUNT`) VALUES (2, '7ab52a522fe8cd58e1c890330bdf4aedd4ce9508b0cb8fc369c3ca1beedbd3c51c61d388b0cc80e2ce09da5eb345aeffb06f912cfa75f970cec9c8b8e437cf3a', '6e49137fa98afdfbb850504708ba428846eafbf089ee44a5748f523c010dccd678b0a033498449211f38ca6e603a74c3ebfd5c295479709367608f5bb0c3dc7b', '김봉재', 'BKCompany', '2016-03-24', 5, 1, 3);
INSERT INTO `tbl_user` (`USER_NUMSEQ`, `USER_ID`, `USER_PASSWORD`, `USER_NAME`, `USER_COMPANY`, `USER_REGDATE`, `USER_SHCOUNT`, `USER_ASCOUNT`, `USER_THREATCOUNT`) VALUES (4, 'fce217d55fb387ac629a3cae356c5d374a0bd6fc939c3100bafa67a7fc8531b8f52a19997b992f5a7833432331b084c6507039e6053339fc703276e6b098c991', '6e49137fa98afdfbb850504708ba428846eafbf089ee44a5748f523c010dccd678b0a033498449211f38ca6e603a74c3ebfd5c295479709367608f5bb0c3dc7b', 'Bongjae', 'CompanyBK', '2016-04-21', 4, 1, 1);
INSERT INTO `tbl_user` (`USER_NUMSEQ`, `USER_ID`, `USER_PASSWORD`, `USER_NAME`, `USER_COMPANY`, `USER_REGDATE`, `USER_SHCOUNT`, `USER_ASCOUNT`, `USER_THREATCOUNT`) VALUES (3, '6e06f7f5c9f9d3e869f21af16009bfc75c387cef96a636f58b4ca5d4c96d4728c63a733e8c82ecd592989dbc765a04398efcb4657ed13de9f0d80a2d09ce3991', '6e49137fa98afdfbb850504708ba428846eafbf089ee44a5748f523c010dccd678b0a033498449211f38ca6e603a74c3ebfd5c295479709367608f5bb0c3dc7b', '봉재 김', 'KB', '2016-03-29', 0, 0, 0);
