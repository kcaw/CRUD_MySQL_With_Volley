<?php

    include_once('connection.php');

    $nip          =$_POST['nip'];

    $nama_dosen   =$_POST['nama_dosen'];
    $prodi_dosen  =$_POST['prodi_dosen'];
    $matkul_dosen =$_POST['matkul_dosen'];

    $getdata = mysqli_query($koneksi,"SELECT * FROM tb_dosen WHERE nip = '$nip'");
    $rows = mysqli_num_rows($getdata);
   
    $response = array();

    if($rows > 0 )
    {
         $query = "UPDATE tb_dosen SET nama_dosen='$nama_dosen',prodi_dosen='$prodi_dosen',matkul_dosen='$matkul_dosen' 
                   WHERE nip='$nip'";
    
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