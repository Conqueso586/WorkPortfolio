import os
import argparse
import cv2
import mediapipe as mp


def process_img(img, face_detection):
    H, W, _ = img.shape
    img_rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    out = face_detection.process(img_rgb)

    # print(out.detections)

    if out.detections is not None:
        for detection in out.detections:
            location_data = detection.location_data
            bbox = location_data.relative_bounding_box

            x1, y1, w, h = bbox.xmin, bbox.ymin, bbox.width, bbox.height

            x1 = int(x1 * W)
            y1 = int(y1 * H)
            w = int(w * W)
            h = int(h * H)

            # img = cv2.rectangle(img, (x1, y1), (x1 + w, y1 + h), (0, 255, 0), 10)

            # blur faces
            img[y1:y1 + h, x1:x1 + w, :] = cv2.blur(img[y1:y1 + h, x1:x1 + w, :], (100, 100))

    return img

img_path = input("Img path to blur: ")
img_name = input("Img Name: ")
img_ext = input("Ext:")
blur_type = input("Type of image to blur: Options{image, video, webcam}")
if img_path is None:
    img_path = 'E:/Projects/ML Data/Face images'

if img_name is None:
    img_name = "PersonalPortrait"

if img_ext is None:
    img_ext = ".jpg"

if not os.path.exists(img_path):
    os.makedirs(img_path)

img_path_full = os.path.join(img_path, img_name + img_ext)

print(img_path_full)
args = argparse.ArgumentParser()

args.add_argument("--mode", default=blur_type)
args.add_argument("--filePath", default=img_path_full)

args = args.parse_args()

mp_face_detection = mp.solutions.face_detection

with mp_face_detection.FaceDetection(model_selection=0, min_detection_confidence=0.5) as face_detection:
    if args.mode in ["image"]:
        img = cv2.imread(args.filePath)

        img = process_img(img, face_detection)
        cv2.imshow('img', cv2.resize(img, (200,200)))
        cv2.waitKey(0)
        cv2.imwrite(os.path.join(img_path, img_name + "_blurred.jpg"), img)


    elif args.mode in ['video']:
        cap = cv2.VideoCapture(args.filePath)
        ret, frame = cap.read()

        output_video = cv2.VideoWriter(os.path.join(img_path, 'output.mp4'),
                                       cv2.VideoWriter_fourcc(*'MP4V'),
                                       25,
                                       (frame.shape[1], frame.shape[0]))

        while ret:
            frame = process_img(frame, face_detection)

            output_video.write(frame)

            ret, frame = cap.read()

        cap.release()
        output_video.release()

    elif args.mode in ['webcam']:
        cap = cv2.VideoCapture(0)
        ret, frame = cap.read()

        while ret:
            frame = process_img(frame, face_detection)

            cv2.imshow('frame', frame)
            cv2.waitKey(25)

            ret, frame = cap.read()

        cap.release()

    # cv2.imshow('img', img)
    # cv2.waitKey(0)
# save image
