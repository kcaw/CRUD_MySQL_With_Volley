<?php

    include_once('connection.php');

    $id_matkul           =$_POST['id_matkul'];

    $matkul             =$_POST['matkul'];
    $prodi_matkul       =$_POST['prodi_matkul'];
    $dosen_matkul       =$_POST['dosen_matkul'];

    $getdata = mysqli_query($koneksi,"SELECT * FROM tb_matakuliah WHERE id_matkul = '$id_matkul'");
    $rows = mysqli_num_rows($getdata);
   
    $response = array();

    if($rows > 0 )
    {
         $query = "UPDATE tb_matakuliah SET matkul='$matkul',prodi_matkul='$prodi_matkul',dosen_matkul='$dosen_matkul' 
                   WHERE id_matkul='$id_matkul'";
    
         $exequery = mysqli_query($koneksi,$query);
         if($exequery)
        {
            $response['code'] =1;
            $response['message'] = "Update Data Berhasil !";
        }
        else
        {
            $response['code'] =0;
            $response['message'] = "Update Data Gagal !";
        }
    }
    else
    {
        $response['code'] =0;
        $response['message'] = "Update Data Gagal, Data Tidak Ditemukan !";
    }
    

    echo json_encode($response);
?>