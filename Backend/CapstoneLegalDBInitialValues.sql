/* 
* Initialize Capstone Legal Database with the data.
* 
* Following Tables are initialized
* capstonelegalschema.COUNTRY - All Countries
* capstonelegalschema.STATE - Only Indian States and UTs
* capstonelegalschema.DISTRICT - All Indian Districts
* capstonelegalschema.CITY - Major Indian Cities based on India Postal Code data
* capstonelegalschema.CONTACT_TYPE
* capstonelegalschema.LEGAL_PRACTICE_TYPE
* capstonelegalschema.LEGAL_PRACTICE_SUB_CATEGORY
* capstonelegalschema.DEPARTMENT
* capstonelegalschema.ROLE
* capstonelegalschema.COURT_TYPE
* capstonelegalschema.JUDGE_TYPE
* capstonelegalschema.LEGAL_DOCUMENT_TYPE
* capstonelegalschema.CREDENTIALS
*/
\c capstonelegaldevdb;
INSERT INTO capstonelegalschema.COUNTRY (COUNTRY_ID, COUNTRY_NAME, COUNTRY_INITIALS) VALUES
('1d9ee505-1c21-45bd-a089-7abb0604a1f2', 'India', 'IND');

INSERT INTO capstonelegalschema.STATE (STATE_ID, STATE_NAME, STATE_INITIALS,COUNTRY_ID) VALUES 
('d3f6c971-3b23-472d-a09e-1ec8c4ef7d1f','Karnataka','KA','1d9ee505-1c21-45bd-a089-7abb0604a1f2');

INSERT INTO capstonelegalschema.DISTRICT (DISTRICT_ID, DISTRICT_NAME, STATE_ID) VALUES
('7bb577d2-c091-4860-88ba-54ee5533ffc5','Bangalore Urban','d3f6c971-3b23-472d-a09e-1ec8c4ef7d1f');

INSERT INTO capstonelegalschema.CITY (CITY_ID, CITY_NAME, DISTRICT_ID) VALUES
('ea8f1d5b-8b6f-4dcd-9fc4-839b79339dc0','Bangalore ','7bb577d2-c091-4860-88ba-54ee5533ffc5');

INSERT INTO capstonelegalschema.CONTACT_TYPE (CONTACT_TYPE_ID, CONTACT_TYPE_NAME, CONTACT_TYPE_DESCRIPTION) VALUES 
('e8f4e787-2ce7-4b41-a1cd-5d5cd127531d','Petitioner','A Petitioner is the person who brings a lawsuit or initiates legal action against another party. They seek to obtain compensation or other relief for the harm or injury they have suffered.'),
('1b3f3167-3309-489a-b623-1b603e4ef221','Defendant','The defendant is the person or entity being sued by the plaintiff. They are typically accused of causing harm or injury to the plaintiff.'),
('1a03e954-aef4-48d5-84bc-f1e99438b8e3','Witness','A witness is a person who has relevant information about the case and is called upon to provide testimony in court. Witnesses may be called by either the plaintiff or defendant or by the court itself.'),
('e12b2273-c550-488c-abd5-313433afe4f0','Judge','The judge is the person who presides over the case and makes decisions on matters such as admissibility of evidence jury instructions and ultimately the verdict.'),
('8d34f6bf-59ce-456a-8637-4d63f0448334','Jury','In some cases a jury may be selected to determine the outcome of a case. The jury is made up of a group of citizens who are responsible for listening to the evidence presented in court and rendering a verdict based on that evidence.'),
('8e89970e-5dbe-41c9-a70b-a15ac84482a3','Counsel','Lawyers who represent the plaintiff or defendant are called counsel. They present arguments cross-examine witnesses and provide legal advice to their clients throughout the case.'),
('d72f5acc-4eb6-4eeb-a1c8-c1fea827a0d2','Appellants','An appellant is a party (usually the primary applicant) who disagrees with a decision made by the organization and submits an appeal.'),
('56a2403e-6d9b-41ad-a02a-08c766a36303','Appellees','Appellee is the party against whom the appeal is filed and responds to and defends the appeal'),
('36829348-dc8f-49c7-8e1d-bd5b1417f2f1','Company','Customer of type business');

INSERT INTO capstonelegalschema.ORGANIZATION (ORGANIZATION_ID, ORGANIZATION_NAME, ORGANIZATION_DESCRIPTION, ORGANIZATION_EMAIL, ORGANIZATION_PHONE, ORGANIZATION_COUNTRY_ID, ORGANIZATION_STATE_ID, ORGANIZATION_DISTRICT_ID, ORGANIZATION_CITY_ID, ORGANIZATION_STREET_1, ORGANIZATION_STREET_2, ORGANIZATION_STREET_ZIPCODE) VALUES 
('2e5ececb-ed13-4325-a9e3-5bd426ea449e', 'ACME LEGAL', 'Reduce legal operation difficulties with litigation support services', 'acmelegal@mailinator.com', '903-551-6331', '1d9ee505-1c21-45bd-a089-7abb0604a1f2', 'd3f6c971-3b23-472d-a09e-1ec8c4ef7d1f', '7bb577d2-c091-4860-88ba-54ee5533ffc5', 'ea8f1d5b-8b6f-4dcd-9fc4-839b79339dc0', '#93, 1st floor, Wheeler Rd',' Cox Town', '560043');

INSERT INTO capstonelegalschema.DEPARTMENT (DEPARTMENT_ID, DEPARTMENT_NAME, DEPARTMENT_DESCRIPTION, DEPARTMENT_ORGANIZATION_ID) VALUES
('4f2b54aa-a0f7-41e9-9647-4915ae119c72','Legal Services','The legal Services team in a legal firm is responsible for providing high-quality legal services to clients','2e5ececb-ed13-4325-a9e3-5bd426ea449e'),
('8e989fce-ff31-457b-8dd8-a6757b46aef8','Marketing and Business Development','The marketing and business development department is responsible for promoting the firms services generating new business leads and building relationships with clients and potential clients.','2e5ececb-ed13-4325-a9e3-5bd426ea449e'),
('e61f053a-2fab-48ed-a466-cd217f6f80ec','Knowledge Management','The knowledge management department is responsible for managing the firms knowledge resources including research databases legal precedents and internal training materials.','2e5ececb-ed13-4325-a9e3-5bd426ea449e'),
('f9b20c74-ea59-400d-9d2a-ffb8e9d23997','Administration','The administration department is responsible for managing the day-to-day operations of the firm including facilities management office services and vendor management.','2e5ececb-ed13-4325-a9e3-5bd426ea449e'),
('72d24c31-e0b3-4461-ac75-4b98ff7dca29','Information Technology','The IT department is responsible for managing the firms technology infrastructure including hardware software networks and data security.','2e5ececb-ed13-4325-a9e3-5bd426ea449e'),
('1da56181-da85-4066-8067-244897986552','Human Resources ','The HR department is responsible for managing the firms human resources functions including recruitment hiring benefits administration employee relations and compliance with employment laws and regulations.','2e5ececb-ed13-4325-a9e3-5bd426ea449e'),
('59d2f25c-f0bd-4fdf-bfb7-ff2cdbfb77d5','Finance and Accounting','The finance and accounting department is responsible for managing the firms financial operations including budgeting financial reporting billing collections and cash management.','2e5ececb-ed13-4325-a9e3-5bd426ea449e');

INSERT INTO capstonelegalschema.ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) VALUES
('1fe83213-6731-4772-a5a4-99f805aa7fd2','PARTNER','A partner is a senior member of the firm who has a stake in its ownership and profits. Partners are responsible for managing the firms operations business development and mentoring junior lawyers.'),
('b4f5effd-5082-447c-b1ac-66bb401bf970','ASSOCIATE','An associate is a junior lawyer who typically has several years of experience. Associates are responsible for working on cases under the supervision of partners conducting legal research and drafting legal documents.'),
('58ed07fb-42ab-4f0d-84cc-375b4d997426','PARALEGAL','A paralegal is a non-lawyer who provides support to lawyers in their day-to-day work. Paralegals are responsible for tasks such as conducting research drafting legal documents and managing case files.'),
('024ed047-61f5-43b9-a237-07c1c5102d4a','JUDGE','Judge in a court'),
('efe88e91-aff0-4236-ba05-c8110398ac6b','SUPERADMIN','Administrator of this application'),
('b1f71170-2b2c-401e-8ace-e5596922564c','ORGADMIN','Administrator of an organization'),
('92729ec0-3a16-47d0-bbef-2772dd5052ba','ITADMIN','An IT Admin is responsible for managing the firms technology infrastructure including hardware software and data security systems.'),
('e88523ff-7ef6-44cd-8b8d-75673ce8a6a9','HRADMIN','Admin representing HR Department'),
('e4cbb789-e6db-4f6a-b3f4-5bfa50310472','HRPARTNER','Member of HR Team'),
('0eac019f-8fe2-4673-a30b-d3b204eeadfe','ACCOUNTADMIN','Finance team administrator'),
('c3ac53c6-435f-4f09-8803-23a3b83dc859','ACCOUNTANT','Member of Finance team'),
('a5fb4f74-130e-44a7-a9bc-fbeb78f0ac97','MARKETINGMANAGER','Manager of the Marketing team'),
('f0832471-f513-4b57-a19d-e87dee7ae9f8','MARKETINGEXECUTIVE','Member of Marketing team'),
('ddec66e1-1be0-4a61-b41a-c22973a98977','CUSTOMER','Customer of an organization');

INSERT INTO capstonelegalschema.COURT_TYPE (COURT_TYPE_ID, COURT_TYPE_NAME, COURT_TYPE_DESCRIPTION) VALUES
('b7deb3b8-4394-4da4-b660-681c26f00130','Supreme Court','It is the Apex court of India'),
('58d85276-9466-400b-97aa-63fb4ba7c37e','High Court','High Courts are the highest judicial body at the State level'),
('c31bc0dd-f2f2-4c84-986a-2effe6fdd3cd','District Civil Court','District Civil Courts are established by the State Governments of India for every district or group of districts and deals with civil cases'),
('c8fb830c-4640-467d-b11f-cb22a2a939e8','District Criminal Court','District Criminal Courts are established by the State Governments of India for every district or group of districts and deals with criminal cases'),
('9c4e150e-b633-4156-acb4-aa21b7a04612','Lok Adalat or Village Court','These are subordinate courts at the village level which provide a system for alternate dispute resolution in villages'),
('d0eeed24-847e-4691-b3f1-0ae6c55010be','Tribunal','Special Tribunals for the administration of specific matters such as tax cases land cases consumer cases etc.');

INSERT INTO capstonelegalschema.JUDGE_TYPE (JUDGE_TYPE_ID, JUDGE_TYPE_NAME, JUDGE_TYPE_DESCRIPTION) VALUES
('6bab57de-7512-4b6f-88c5-bd694bdc51a4','Chief Justice of India','The Chief Justice of India is the head of the Supreme Court of India'),
('57463e90-fb28-4aad-b29d-95a9b21e21e6','Sitting Judge of the Supreme Court','Justice in Supreme Court of India'),
('f7ee81dd-38ff-423e-850e-4559a04daaaa','Chief Justice of the High Court','The Chief Justice of the High Court of a state'),
('c028f41e-68c8-4ddd-ad8a-2bd339e89f72','Justice of the High Court','Justice in High Court of a state'),
('267a1bc7-011c-4f6c-8d3c-9b57bd38af1f','Principal District Judge','The district judge presides over civil matters'),
('73fdf0ec-bbe7-4b60-a1ee-a5c3eae3434a','Principal District Session Judge ','The district sessions judge presides over criminal matters'),
('f0287bd3-fead-4a2a-8b96-a21213c37d37','Additional District Judge','Depending on workload additional district judges are appointed'),
('4e78b5b1-2cf0-429a-a30f-de834bd80ba4','Chief Judicial Magistrate ','Chief Judge in the second tier court in the criminal court structure in India.'),
('6bf8749c-8326-42fd-9e14-1330fc051a13','Judicial Magistrate 1st Class ','Judge in the second tier court in the criminal court structure in India.'),
('bba496b8-2486-42df-9ddd-c8c79518ee7b','Chief Metropolitan Magistrate','Chief Judge in magistrate courts those are situated in a division headquarter or metropolitan city'),
('e4d9e8ea-4209-4ed1-80fb-25ebfca5d99f','Metropolitan Magistrate','Judge in magistrate courts those are situated in a division headquarter or metropolitan city deals with criminal cases'),
('0030a4eb-7cf7-43bc-aaa0-27986678fdf1','Sub-Judge','Judge in magistrate courts those are situated in a division headquarter or metropolitan city deals with civil cases'),
('384c5390-d9b1-4c6b-8f77-6ebfb2ab9462','District Munsif','District Munsif is the judge of the lowest order handling matters pertaining to civil matters in the district');

INSERT INTO capstonelegalschema.LEGAL_DOCUMENT_TYPE (LEGAL_DOCUMENT_TYPE_ID, LEGAL_DOCUMENT_TYPE_NAME, LEGAL_DOCUMENT_TYPE_DESCRIPTION) VALUES
('77a97cb1-99d2-4aae-9685-6054218fcbe0','Nondisclosure Agreement','specific business needs'),
('9f7d5114-3865-41c2-93ff-daeb8013c6d7','Employment Contracts', 'employment contracts including the ability to incorporate data from HR systems and generate contracts with pre-defined clauses and terms.'),
('dd7cd757-760a-4770-bc50-24d08a297fac','Sales Contracts', 'incorporate data from CRM systems automate the calculation of prices and generate contracts with pre-defined clauses and terms.'),
('af4348a4-0632-43e9-bf2e-74e531bbc71f','Real Estate Contracts','incorporate data from property listing systems automate the calculation of prices and generate contracts with pre-defined clauses and terms.'),
('9f72a326-291c-464d-8eb7-88038b6f62af','Legal briefs', 'incorporate data from legal research systems and generate briefs with pre-defined clauses and terms.'),
('b1c7629f-b73b-4ead-bc1b-77ddf5388896','Legal forms', 'legal forms such as power of attorney wills and trusts including the ability to incorporate data from legal research systems and generate forms with pre-defined clauses and terms.'),
('980b89a7-2b25-4bbf-8479-2fab43be36e7','Compliance Documents','compliance-related documents such as GDPR-compliant data processing agreements HIPAA-compliant business associate agreements and FCPA-compliant anti-corruption policies.'),
('9b695a98-fb51-44b7-a72a-7985722e5621','Legal Notices','eviction notices foreclosure notices and summons including the ability to incorporate data from legal research systems and generate forms with pre-defined clauses and terms.');

CREATE VIEW capstonelegalschema.JUDGE_VIEW AS 
SELECT 
    J.JUDGE_ID, 
    J.JUDGE_FIRST_NAME, 
    J.JUDGE_LAST_NAME, 
    JT.JUDGE_TYPE_NAME, 
    J.JUDGE_EMAIL, 
    CT.COURT_TYPE_NAME, 
    J.JUDGE_STREET_1, 
    J.JUDGE_STREET_2, 
    CI.CITY_NAME AS JUDGE_CITY_NAME, 
    DI.DISTRICT_NAME AS JUDGE_DISTRICT_NAME, 
    ST.STATE_NAME AS JUDGE_STATE_NAME, 
    CO.COUNTRY_NAME AS JUDGE_COUNTRY_NAME
FROM 
    capstonelegalschema.JUDGE J 
INNER JOIN 
    capstonelegalschema.JUDGE_TYPE JT ON J.JUDGE_TYPE_ID = JT.JUDGE_TYPE_ID
INNER JOIN 
    capstonelegalschema.COURT C ON J.JUDGE_COURT_ID = C.COURT_ID
INNER JOIN 
    capstonelegalschema.COURT_TYPE CT ON C.COURT_TYPE_ID = CT.COURT_TYPE_ID
INNER JOIN 
    capstonelegalschema.CITY CI ON J.JUDGE_CITY_ID = CI.CITY_ID
INNER JOIN 
    capstonelegalschema.DISTRICT DI ON J.JUDGE_DISTRICT_ID = DI.DISTRICT_ID
INNER JOIN 
    capstonelegalschema.STATE ST ON J.JUDGE_STATE_ID = ST.STATE_ID
INNER JOIN 
    capstonelegalschema.COUNTRY CO ON J.JUDGE_COUNTRY_ID = CO.COUNTRY_ID;
