import com.crystaldecisions.sdk.exception.SDKException;
import com.crystaldecisions.sdk.occa.infostore.IInfoObject;
import com.crystaldecisions.sdk.occa.infostore.IInfoObjects;
import com.crystaldecisions.sdk.plugin.desktop.folder.IFolder;
import com.crystaldecisions.sdk.properties.IProperties;
import com.crystaldecisions.sdk.properties.IProperty;


public class FolderReportStructure extends InitCmsExcel {

	public static void main(String[] args) {
		try {
			FolderReportStructure folder = new FolderReportStructure();
			
			initcmsexcel.setCMS(args[0]);
			initcmsexcel.setPassword(args[1]);
			initcmsexcel.setFolderID(args[2]);
			
			initcmsexcel.initCMSConnection();
			initcmsexcel.initExcel();
			
			
			initcmsexcel.addRow("Report Name|Report Owner|Creation Time|Last Run Time|Last Modified Date|Report Type|Report CUID|Report Folder Path");
			folder.generateExcel();
			initcmsexcel.setFilename();
			initcmsexcel.saveExcel();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void generateExcel() throws SDKException {
		String sq = "SELECT TOP 50000 SI_NAME, SI_CUID, SI_KIND, SI_CREATION_TIME, SI_UPDATE_TS, SI_LAST_RUN_TIME, SI_OWNER, SI_SIZE, SI_PARENTID, SI_FILES, SI_PARENT_FOLDER FROM CI_INFOOBJECTS, CI_SYSTEMOBJECTS, CI_APPOBJECTS WHERE SI_KIND IN ( 'Webi', 'CrystalReport', 'FullClient') AND SI_INSTANCE = 0 AND SI_ANCESTOR=" + initcmsexcel.folderid;
		
		IInfoObjects iObjects = iStore.query(sq);
		IInfoObject iObject = null;
		
		for (int i = 0; i < iObjects.size(); i++) {
			StringBuilder rowData = new StringBuilder();
			iObject = (IInfoObject) iObjects.get(i);
			
			IProperties prop = iObject.properties();
			
			IProperty getProp = prop.getProperty("SI_PARENTID");
		
			IProperty owner = prop.getProperty("SI_OWNER");
			
			IProperty creationtime = prop.getProperty("SI_CREATION_TIME");
			
			IProperty lastmodifiedtime = prop.getProperty("SI_UPDATE_TS");
			
			IProperty lastruntime = prop.getProperty("SI_LAST_RUN_TIME");
						
			rowData.append(iObject.getTitle());
			rowData.append("|");
		
			rowData.append(owner.toString());
			rowData.append("|");
			
			rowData.append(creationtime);
			rowData.append("|");
			
			rowData.append(lastruntime);
			rowData.append("|");
			
			rowData.append(lastmodifiedtime);
			rowData.append("|");
				
			rowData.append(iObject.getKind());
			rowData.append("|");
			
		  	rowData.append(iObject.getCUID());
			rowData.append("|");
			
								
			String FolderID = getProp.toString();
			
			IInfoObjects folder = iStore.query("select si_id,si_CUID,si_name,si_parentid,si_path from ci_infoobjects where si_id="+ FolderID);
			IInfoObject ifolder = (IInfoObject) folder.get(0);
			if (ifolder.getKind().equals("Folder")) {
				IFolder iifolder = (IFolder) ifolder;
				String finalFolderPath = "";
				if (iifolder.getPath() != null) {
					String path[] = iifolder.getPath();
					for (int fi = 0; fi < path.length; fi++) {
						finalFolderPath = path[fi] + "/" + finalFolderPath;
					}
					finalFolderPath = finalFolderPath + iifolder.getTitle();
				} else {
					finalFolderPath = finalFolderPath + iifolder.getTitle();
				}

				rowData.append(finalFolderPath);
			} else if ((ifolder.getKind().equals("FavoritesFolder"))) {
				rowData.append("FavoritesFolder ::  ");
				rowData.append(ifolder.getTitle());
			} else if ((ifolder.getKind().equals("Inbox"))) {
				rowData.append(" Inbox ::  ");
				rowData.append(ifolder.getTitle());
			} else if ((ifolder.getKind().equals("ObjectPackage"))) {
				IProperties prop1 = ifolder.properties();
				IProperty getProp1 = prop1.getProperty("SI_PARENTID");
				String FolderID1 = getProp1.toString();
				IInfoObjects folder1 = iStore
						.query("select * from ci_infoobjects where si_id="
								+ FolderID1);
				IInfoObject ifolder1 = (IInfoObject) folder1.get(0);
				if (ifolder1.getKind().equals("Folder")) {
					IFolder iifolder1 = (IFolder) ifolder1;
					String finalFolderPath1 = "";
					if (iifolder1.getPath() != null) {
						String path[] = iifolder1.getPath();
						for (int j = 0; j < path.length; j++) {
							finalFolderPath1 = path[j] + "/" + finalFolderPath1;
						}
						finalFolderPath1 = finalFolderPath1
								+ iifolder1.getTitle() + "/"
								+ ifolder.getTitle();
					} else {
						finalFolderPath1 = finalFolderPath1
								+ iifolder1.getTitle() + "/"
								+ ifolder.getTitle();
					}
					rowData.append(finalFolderPath1);
				}
			}
			
						
			System.out.println(rowData.toString());
			initcmsexcel.addRow(rowData.toString());
		}

		
	}

}
