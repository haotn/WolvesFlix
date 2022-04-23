import sys
import os
import pandas as pd
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import CountVectorizer
from numpy.core._multiarray_umath import dtype
from _io import open
import string


def getVideoId(string_db):
    list = string_db.split('{seperator}')
    return int(list[0])


def handle_db(string_db):
    original = string_db.split('{seperator}')
    str_handle = original[1]
    str_handle = str_handle
    list_return = []
    arr_video = str_handle.split('{row}')
    for item in arr_video:
        arr_attr = item.split('{col}')
        list_return.append(arr_attr)
    return list_return


def recommender(path):
    string_db = ''
    with open(path + '/input.txt', "r", encoding="UTF-8") as reader:
        string_db = reader.read()
        reader.close()
    data = np.array(handle_db(string_db), dtype=object)
    mv = getVideoId(string_db)
    df = pd.DataFrame(data, columns=['VideoId', 'Title', 'AddDate', 'Comments', 'Replys', 'Likes', 'Shares', 'Views', 'Genres'])
    important_feature = []
    for i in range(0, df.shape[0]):
        important_feature.append(df['VideoId'][i] + ' ' + df['Title'][i] + ' ' + df['Likes'][i] + ' ' + df['Views'][i] + ' ' + str(df['Genres'][i]))
    df['important_feature'] = important_feature
    #Mã hóa dưới dạng số
    cm = CountVectorizer().fit_transform(df['important_feature'])
    #Độ tương tự cosin, tính độ tương tự dưới dạng tích số chấm chuẩn hóa của X và Y
    cs = cosine_similarity(cm)
    scores = list(enumerate(cs[int(mv)]))
    sorted_scores = sorted(scores, key=lambda x:x[1], reverse=True)
    sorted_scores = sorted_scores[1:]
    # print(sorted_scores)
    to_write = ''
    index = 0
    for item in sorted_scores:
        # id_in_scores += str(item[0] + 1)
        # print(df.iloc[[item[0]]]['VideoId'])
        to_write += str(int(df.iloc[[item[0]]]['VideoId']))
        # print(to_write)
        index += 1
        if(index < sorted_scores.__len__()):
            to_write += ';'
        else:
            print(to_write)


if __name__ == '__main__':
    # data = '''6{seperator}1{col}Hà Anh Tuấn - Tháng Tư Là Lời Nói Dối Của Em (Official MV){col}2022-01-06 00:00:00.0{col}6{col}25{col}89{col}7{col}77{col}Âm nhạc-Tình yêu{row}2{col}HIEUTHUHAI - Bật Nhạc Lên ft. Harmonie (prod. by NEMYA){col}2020-02-04 00:00:00.0{col}5{col}19{col}99{col}8{col}78{col}Âm nhạc-Tình yêu{row}3{col}Kygo, Zara Larsson, Tyga - Like It Is{col}2020-09-20 00:00:00.0{col}1{col}21{col}93{col}8{col}72{col}Âm nhạc-Tình yêu{row}4{col}Passenger - Let Her Go (Official Video){col}2021-04-15 00:00:00.0{col}10{col}24{col}108{col}6{col}83{col}Âm nhạc-Tình yêu{row}5{col}MONSTAR - GIỮ LẤY LÀM GÌ | Official Music Video{col}2022-12-13 00:00:00.0{col}15{col}19{col}95{col}6{col}79{col}Âm nhạc-Tình yêu{row}6{col}24H | OFFICIAL MUSIC VIDEO | LYLY ft MAGAZINE{col}2021-08-19 00:00:00.0{col}9{col}22{col}98{col}6{col}78{col}Âm nhạc-Tình yêu{row}7{col}JustaTee - Love you too much (Official MV){col}2021-06-02 00:00:00.0{col}6{col}28{col}81{col}8{col}73{col}Âm nhạc-Tình yêu{row}8{col}Ex's Hate Me - B Ray x Masew (Ft AMEE) | Official MV{col}2020-06-22 00:00:00.0{col}15{col}16{col}95{col}9{col}71{col}Âm nhạc-Tình yêu{row}9{col}Đen - Lối Nhỏ ft. Phương Anh Đào (M/V){col}2021-03-18 00:00:00.0{col}6{col}22{col}111{col}7{col}117{col}Âm nhạc-Tình yêu{row}10{col}Đen - Trốn Tìm ft. MTV band (M/V){col}2021-01-19 00:00:00.0{col}12{col}21{col}83{col}4{col}75{col}Âm nhạc-Tình yêu{row}11{col}YÊU ĐƠN PHƯƠNG | ONLYC x KARIK | OFFICIAL MV{col}2022-07-05 00:00:00.0{col}8{col}19{col}93{col}7{col}72{col}Âm nhạc-Tình yêu{row}12{col}EM NGỦ CHƯA? | OFFICIAL MV | Trịnh Thăng Bình x OSAD{col}2020-01-09 00:00:00.0{col}4{col}11{col}100{col}12{col}80{col}Âm nhạc-Tình yêu{row}13{col}Kha - Em Không Cô Đơn (Official MV){col}2022-03-11 00:00:00.0{col}10{col}18{col}100{col}9{col}119{col}Âm nhạc-Tình yêu{row}14{col}Long Cao - BÊN ẤY BÊN NÀY (Audio){col}2020-12-09 00:00:00.0{col}10{col}14{col}100{col}11{col}105{col}Âm nhạc-Tình yêu{row}15{col}Chuyện Đôi Ta - Emcee L (Da LAB) ft Muộii (Official MV){col}2022-11-01 00:00:00.0{col}6{col}20{col}106{col}5{col}76{col}Âm nhạc-Tình yêu{row}16{col}VÌ MỘT CÂU NÓI - HOÀNG DŨNG, MÀU NƯỚC BAND | 25 MÉT VUÔNG - EP.3{col}2022-04-18 00:00:00.0{col}6{col}18{col}97{col}11{col}93{col}Âm nhạc-Tình yêu{row}17{col}Chẳng Nói Nên Lời - Acoustic Session | Hoàng Dũng{col}2020-02-10 00:00:00.0{col}7{col}22{col}96{col}5{col}68{col}Âm nhạc-Tình yêu{row}18{col}ĐỢI // VŨ. (ORIGINAL){col}2020-09-29 00:00:00.0{col}5{col}20{col}99{col}11{col}83{col}Âm nhạc-Tình yêu{row}19{col}NGHE EM{col}2020-03-26 00:00:00.0{col}8{col}19{col}104{col}10{col}83{col}Âm nhạc-Tình yêu{row}20{col}Thắc Mắc (MĐX){col}2020-11-19 00:00:00.0{col}9{col}27{col}104{col}10{col}85{col}Âm nhạc-Tình yêu{row}21{col}THÓI QUEN - HOÀNG DŨNG, GDUCKY, MÀU NƯỚC BAND | 25 MÉT VUÔNG - EP.1{col}2021-06-27 00:00:00.0{col}6{col}18{col}96{col}7{col}69{col}Âm nhạc-Tình yêu{row}22{col}SUYT NUA THI | OFFICIAL OST | CHUYEN DI CUA THANH XUAN | ANDIEZ x BITI'S HUNTER{col}2022-04-21 00:00:00.0{col}6{col}15{col}102{col}10{col}83{col}Âm nhạc-Tình yêu{row}23{col}NƠI NÀY CÓ ANH | OFFICIAL MUSIC VIDEO | SƠN TÙNG M-TP{col}2022-09-03 00:00:00.0{col}10{col}19{col}93{col}5{col}90{col}Âm nhạc-Tình yêu{row}24{col}Finn Askew - Roses (Official Video){col}2022-03-15 00:00:00.0{col}7{col}26{col}104{col}7{col}83{col}Âm nhạc-Tình yêu{row}25{col}LẠC TRÔI | OFFICIAL MUSIC VIDEO | SƠN TÙNG M-TP{col}2020-02-22 00:00:00.0{col}13{col}17{col}101{col}11{col}68{col}Âm nhạc-Tình yêu'''
    # agrs = 'E:/Eclipse-Workspace-Java4/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PC01545_TieuNhutHao_Assignment/Recommendator'
    recommender(sys.argv[1])
    # recommender(agrs)
