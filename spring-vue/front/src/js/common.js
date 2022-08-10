import * as XLSX from "xlsx";

const excelDownload =(list)=>{
  const workbook = XLSX.utils.book_new();
  const workSheet = XLSX.utils.json_to_sheet(list);
  XLSX.utils.book_append_sheet(workbook, workSheet, 'test');
  XLSX.writeFile(workbook, 'example.xlsx');
}

export default excelDownload
